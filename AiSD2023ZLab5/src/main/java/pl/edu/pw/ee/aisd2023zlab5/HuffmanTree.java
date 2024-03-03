package pl.edu.pw.ee.aisd2023zlab5;

import java.io.FileInputStream;
import java.io.IOException;

public class HuffmanTree {
    private Node root;
    private final String[] codes = new String[256];

    public HuffmanTree(String fileName) {
        createHuffmanTree(fileName);
    }

    public HuffmanTree(Node root) {
        traverseTree(root, "", "");
    }

    public Node getRoot() {
        return root;
    }

    private void createHuffmanTree(String fileName) {
        PriorityHeap<Node> lettersHeap = fulfillHeap(fileName);

        while (lettersHeap.getLastNodeId() > 0) {
            Node firstLowest = lettersHeap.extractMin();
            Node secondLowest = lettersHeap.extractMin();
            Node newNode = new Node((char) 0, firstLowest.getQuantity() + secondLowest.getQuantity(), firstLowest, secondLowest);

            lettersHeap.insertToHeap(newNode);
        }

        root = lettersHeap.extractMin();
        traverseTree(root, "", "");
    }

    private PriorityHeap<Node> fulfillHeap(String fileName) {
        int[] ascii = new int[256];
        int n = 0;

        try (FileInputStream fileReader = new FileInputStream(fileName)) {
            int asciiSign;

            while ((asciiSign = fileReader.read()) != -1) {
                if (ascii[asciiSign]++ == 0) {
                    n++;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PriorityHeap<Node> lettersHeap;

        if (n == 0) {
            throw new IllegalArgumentException("Plik jest pusty!");
        } else if (n == 1) {
            n++;
            lettersHeap = new PriorityHeap<>(n);
            Node fakeNode = new Node((char) 0, 0);
            lettersHeap.insertToHeap(fakeNode);
        } else {
            lettersHeap = new PriorityHeap<>(n);
        }

        for (int i = 0; i < 256; i++) {
            if (ascii[i] != 0) {
                Node node = new Node((char) i, ascii[i]);
                lettersHeap.insertToHeap(node);
            }
        }

        return lettersHeap;
    }

    private void traverseTree(Node node, String code, String param) {
        code += param;
        if (node.isLeaf()) {
            this.codes[node.getLetter()] = code;
            return;
        }
        traverseTree(node.getLeft(), code, "0");
        traverseTree(node.getRight(), code, "1");
    }

    public String[] getCodes() {
        return codes;
    }
}
