package pl.edu.pw.ee.aisd2023zlab5;

import java.io.FileInputStream;
import java.io.IOException;

public class PriorityHeap {
    private Node[] letters;
    private int lastId = -1;

    public PriorityHeap(int size) {
        this.letters = new Node[size];
    }

    public PriorityHeap(String fileName) {
        createHeap(fileName);
    }

    public Node[] getArray() {
        return letters;
    }

    public int getLastNodeId() {
        return lastId;
    }

    private void createHeap(String fileName) {
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

        if (n == 0) {
            throw new IllegalArgumentException("Plik jest pusty!");
        } else if (n == 1) {
            n++;
            letters = new Node[n];
            Node fakeNode = new Node((char) 0, 0);
            insertToHeap(fakeNode);
        } else {
            letters = new Node[n];
        }

        for (int i = 0; i < 256; i++) {
            if (ascii[i] != 0) {
                Node node = new Node((char) i, ascii[i]);
                insertToHeap(node);
            }
        }
    }

    private void heapDown() {
        int parentId = 0;
        int leftChildId = 1;
        int rightChildId = 2;
        int lowestQuantityId = parentId;

        while (true) {
            if (leftChildId <= lastId && letters[leftChildId].compareTo(letters[lowestQuantityId]) < 0) {
                lowestQuantityId = leftChildId;
            }

            if (rightChildId <= lastId && letters[rightChildId].compareTo(letters[lowestQuantityId]) < 0) {
                lowestQuantityId = rightChildId;
            }

            if (lowestQuantityId != parentId) {
                swap(parentId, lowestQuantityId);
            } else break;

            parentId = lowestQuantityId;
            leftChildId = 2 * parentId + 1;
            rightChildId = 2 * parentId + 2;
        }
    }

    private void heapUp() {
        int i = lastId;
        int parent;
        if (i % 2 == 0) {
            parent = i / 2 - 1;
        } else {
            parent = i / 2;
        }

        while ((i >= 1) && letters[parent].compareTo(letters[i]) > 0) {
            swap(parent, i);
            i = parent;
            if (i % 2 == 0) {
                parent = i / 2 - 1;
            } else {
                parent = i / 2;
            }
        }

    }

    private void swap(int firstId, int secondId) {
        if (firstId != secondId) {

            Node firstNode = letters[firstId];
            letters[firstId] = letters[secondId];
            letters[secondId] = firstNode;
        }
    }

    public Node extractMin() {
        if (lastId < 0) {
            throw new RuntimeException("Kopiec jest już pusty!");
        }
        Node min = letters[0];
        letters[0] = letters[lastId];
        lastId--;
        heapDown();

        return min;
    }

    public void insertToHeap(Node newNode) {
        lastId++;
        if (lastId >= letters.length) {
            throw new RuntimeException("Kopiec jest już pełny!");
        }

        letters[lastId] = newNode;
        heapUp();
    }
}
