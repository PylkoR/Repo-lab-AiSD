package pl.edu.pw.ee.aisd2023zlab5;

import java.io.FileInputStream;
import java.io.IOException;

public class PriorityHeap {
    private Node[] letters;
    private int lastId;


    public PriorityHeap(String fileName) {
        this.letters = createHeap(fileName);
        this.lastId = letters.length - 1;
    }

    public Node[] getArray() {
        return letters;
    }

    public int getSize() {
        return lastId;
    }

    private Node[] createHeap(String fileName) {
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

        Node[] letters = new Node[n];
        int k = 0;

        for (int i = 0; i < 256; i++) {
            if (ascii[i] != 0) {
                Node node = new Node((char) i, ascii[i]);
                letters[k++] = node;
            }
        }

        buildHeap(letters);

        return letters;
    }

    private void buildHeap(Node[] letters) {
        int n = letters.length - 1;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(letters, i, n);
        }
    }

    private void heapify(Node[] letters, int parentId, int maxId) {
        int leftChildId = 2 * parentId + 1;
        int rightChildId = 2 * parentId + 2;
        int lowestQuantity = parentId;

        if (leftChildId <= maxId && letters[leftChildId].getQuantity() < letters[lowestQuantity].getQuantity()) {
            lowestQuantity = leftChildId;
        }

        if (rightChildId <= maxId && letters[rightChildId].getQuantity() < letters[lowestQuantity].getQuantity()) {
            lowestQuantity = rightChildId;
        }

        if (lowestQuantity != parentId) {
            swap(letters, parentId, lowestQuantity);
            heapify(letters, lowestQuantity, maxId);
        }
    }

    private void swap(Node[] heap, int firstId, int secondId) {
        if (firstId != secondId) {

            Node firstNode = heap[firstId];
            heap[firstId] = heap[secondId];
            heap[secondId] = firstNode;
        }
    }

    public Node extractMin() {
        if (lastId < 0) {
            throw new RuntimeException("Kopiec jest już pusty");
        }
        Node min = letters[0];
        letters[0] = letters[lastId];
        lastId--;
        heapify(letters, 0, lastId);

        return min;
    }

    public void insertToHeap(Node newNode) {
        lastId++;
        int i = lastId;
        int parent;
        if (i % 2 == 0) {
            parent = i / 2 - 1;
        } else {
            parent = i / 2;
        }

        while ((i > 1) && letters[parent].getQuantity() > newNode.getQuantity()) {
            letters[i] = letters[parent];
            i = parent;
            if (parent % 2 == 0) {
                parent = parent / 2 - 1;
            } else {
                parent = parent / 2;
            }
        }

        letters[i] = newNode;
    }
}
