package pl.edu.pw.ee.aisd2023zlab5;

import java.util.HashMap;

public class HuffmanTree {
    private Node root;
    private HashMap<Character, String> codes;

    public HuffmanTree(String fileName) {
        this.root = createHuffmanTree(fileName);
        this.codes = new HashMap<>();
    }

    private Node createHuffmanTree(String fileName) {
        PriorityHeap heap = new PriorityHeap(fileName);
        while (heap.getSize() > 0) {
            Node firstLowest = heap.extractMin();
            Node secondLowest = heap.extractMin();
            System.out.println(firstLowest.getLetter() + "->" + firstLowest.getQuantity() + " + " + secondLowest.getLetter() + "->" + secondLowest.getQuantity() + "=" + (Integer) (firstLowest.getQuantity() + secondLowest.getQuantity()));
            Node newNode = new Node((char) 0, firstLowest.getQuantity() + secondLowest.getQuantity(), firstLowest, secondLowest);

            heap.insertToHeap(newNode);
        }

        return heap.getArray()[0];
    }

    public Node getRoot() {
        return root;
    }


    private void traverseTree(Node node, String code, String param) {
        code += param;
        if (node.isLeaf()) {
            this.codes.put((Character) node.getLetter(), code);
            return;
        }
        traverseTree(node.getLeft(), code, "0");
        traverseTree(node.getRight(), code, "1");
    }

    public void createCodes() {
        traverseTree(this.getRoot(), "", "");
    }

    public HashMap<Character, String> getCodes() {
        return this.codes;
    }


}
