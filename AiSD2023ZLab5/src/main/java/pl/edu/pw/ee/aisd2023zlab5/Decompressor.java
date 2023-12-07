package pl.edu.pw.ee.aisd2023zlab5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decompressor {
    Node root;
    int read = 4;
    int currentByte;
    int nextByte;

    public void decompress(String fileToDecompress, String outputFile) {
        try (FileInputStream fileReader = new FileInputStream(fileToDecompress)) {
            //DRZEWO
            currentByte = fileReader.read();
            nextByte = fileReader.read();
            int filled = (currentByte & 0b1110_0000) >> 5;
            System.out.println("filled: " + filled);

            root = new Node('r', 0, false);
            recreateTree(root, fileReader);

            HuffmanTree hufTree = new HuffmanTree(root);

            String[] codes = hufTree.getCodes();
            for (int i = 0; i < codes.length; i++) {
                if (codes[i] != null) {
                    System.out.println((char) i + " - " + codes[i]);
                }
            }

            //TEKST
            try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                int bit;
                Node currentNode = root;

                while ((bit = readBit(fileReader)) != -1) {
                    if (bit == 0) {
                        currentNode = currentNode.getLeft();
                    } else {
                        currentNode = currentNode.getRight();
                    }

                    if (currentNode.isLeaf()) {
                        outputStream.write(currentNode.getLetter());

                        currentNode = root;
                    }
                }

                for (int i = 0; i < filled; i++) {
                    if ((currentByte & 0b1000_0000) == 0) {
                        currentNode = currentNode.getLeft();
                    } else {
                        currentNode = currentNode.getRight();
                    }
                    currentByte = currentByte << 1;

                    if (currentNode.isLeaf()) {
                        outputStream.write(currentNode.getLetter());

                        currentNode = root;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void recreateTree(Node currentNode, FileInputStream fileReader) throws IOException {
        Node newLeftNode;
        Node newRightNode;

        int bit = readBit(fileReader);
        if (bit != 0) {
            char letter = 0;
            for (int i = 0; i < 8; i++) {
                letter = (char) (letter << 1);
                int letterBit = readBit(fileReader);

                if (letterBit != 0 && letterBit != -1) {
                    letter++;
                } else if (letterBit == -1) {
                    throw new RuntimeException("kompresowany plik był zbyt mały!");
                }
            }

            newLeftNode = new Node(letter, 1, true);
            currentNode.setLeft(newLeftNode);
        } else {
            newLeftNode = new Node('#', 0, false);
            currentNode.setLeft(newLeftNode);
            recreateTree(newLeftNode, fileReader);
        }

        bit = readBit(fileReader);
        if (bit != 0) {
            char letter = 0;
            for (int i = 0; i < 8; i++) {
                letter = (char) (letter << 1);
                int letterBit = readBit(fileReader);

                if (letterBit != 0 && letterBit != -1) {
                    letter++;
                } else if (letterBit == -1) {
                    throw new RuntimeException("kompresowany plik był zbyt mały!");
                }
            }

            newRightNode = new Node(letter, 1, true);
            currentNode.setRight(newRightNode);
        } else {
            newRightNode = new Node('#', 0, false);
            currentNode.setRight(newRightNode);
            recreateTree(newRightNode, fileReader);
        }
    }

    private int readBit(FileInputStream fileReader) throws IOException {
        if (read == 8) {
            currentByte = nextByte;
            nextByte = fileReader.read();
            read = 0;
        }

        int bitReaded = 0b1000_0000 >> read;
        bitReaded = bitReaded & currentByte;
        read++;

        if (nextByte == -1) {
            return -1;
        } else {
            return bitReaded;
        }

    }
}
