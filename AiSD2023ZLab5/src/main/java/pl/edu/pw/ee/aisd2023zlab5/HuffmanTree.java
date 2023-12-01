package pl.edu.pw.ee.aisd2023zlab5;

public class HuffmanTree {
    private Node root;
    private final String[] codes = new String[256];

    public HuffmanTree(String fileName) {
        createHuffmanTree(fileName);
    }

    private void createHuffmanTree(String fileName) {
        PriorityHeap heap = new PriorityHeap(fileName);
        while (heap.getLastNodeId() > 0) {
            Node firstLowest = heap.extractMin();
            Node secondLowest = heap.extractMin();
            System.out.println(firstLowest.getLetter() + "->" + firstLowest.getQuantity() + " + " + secondLowest.getLetter() + "->" + secondLowest.getQuantity() + "=" + (firstLowest.getQuantity() + secondLowest.getQuantity()));
            Node newNode = new Node((char) 0, firstLowest.getQuantity() + secondLowest.getQuantity(), firstLowest, secondLowest);

            heap.insertToHeap(newNode);
        }

        root = heap.getArray()[0];
        traverseTree(root, "", "");
    }

    public Node getRoot() {
        return root;
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
