package pl.edu.pw.ee.aisd2023zlab5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Compressor {
    private String treeToOutputFile = "";

    public void compress(String fileName, String outputFile) throws IOException {
        HuffmanTree tree = new HuffmanTree(fileName);
        String[] codes = tree.getCodes();
        FileChannel output = FileChannel.open(Paths.get(outputFile), StandardOpenOption.WRITE);
        FileChannel input = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ);
        output.truncate(0);

        if (input.size() < 3) {
            throw new RuntimeException("Plik jest za mały, nie nadaje się do kompresji.");
        }

        try (FileInputStream fileReader = new FileInputStream(fileName)) {
            int asciiSign;

            try (RandomAccessFile fileOutput = new RandomAccessFile(outputFile, "rw")) {
                int toWrite = 0;
                int filled = 3;

                compressTreeToString(tree.getRoot());
                System.out.println(treeToOutputFile);       //wypisuje skopmpresowane drzewo jako string

                for (int i = 0; i < treeToOutputFile.length(); i++) {
                    char currentChar = treeToOutputFile.charAt(i);

                    toWrite = toWrite << 1;
                    if (currentChar == '1') {
                        toWrite++;
                        filled++;
                        if (filled == 8) {
                            fileOutput.write(toWrite);
                            //System.out.println((int)toWrite + " 1");
                            toWrite = 0;
                            filled = 0;
                        }
                        i++;
                        currentChar = treeToOutputFile.charAt(i);
                        int charValue = currentChar;
                        for (int k = 0; k < 8; k++) {
                            toWrite = toWrite << 1;
                            if ((charValue & 0b1000_0000) != 0) {
                                toWrite++;
                            }
                            charValue = charValue << 1;
                            filled++;

                            if (filled == 8) {
                                fileOutput.write(toWrite);
                                //System.out.println((int)toWrite + " petla");
                                toWrite = 0;
                                filled = 0;
                            }
                        }

                    } else {
                        filled++;
                        if (filled == 8) {
                            fileOutput.write(toWrite);
                            //System.out.println((int)toWrite + " 0");
                            toWrite = 0;
                            filled = 0;
                        }
                    }
                }

                while ((asciiSign = fileReader.read()) != -1) {
                    String code = codes[asciiSign];

                    for (int i = 0; i < code.length(); i++) {
                        char currentChar = code.charAt(i);

                        toWrite = toWrite << 1;
                        if (currentChar == '1') {
                            toWrite++;
                        }
                        filled++;

                        if (filled == 8) {
                            fileOutput.write(toWrite);
                            //System.out.println((int)toWrite + " tekst");
                            toWrite = 0;
                            filled = 0;
                        }
                    }
                }
                if (filled != 0) {
                    toWrite = toWrite << 8 - filled;
                    fileOutput.write(toWrite);
                    //System.out.println((int)toWrite + " filled");
                    System.out.println("filled: " + filled); //wypisuje wartość filled

                    fileOutput.seek(0);
                    int firstByte = fileOutput.read();
                    filled = filled << 5;
                    firstByte = filled | firstByte;
                    fileOutput.seek(0);
                    fileOutput.write(firstByte);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void compressTreeToString(Node node) {
        if (node.isLeaf()) {
            treeToOutputFile = treeToOutputFile + "1" + node.getLetter();
            return;
        } else {
            treeToOutputFile += "0";
        }
        compressTreeToString(node.getLeft());
        compressTreeToString(node.getRight());
    }
}