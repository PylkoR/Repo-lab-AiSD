package pl.edu.pw.ee.aisd2023zlab5;

public class Node implements Comparable<Node> {
    private final char letter;
    private final int quantity;
    private Node left;
    private Node right;
    private final boolean isLeaf;

    public Node(char letter, int quantity) {
        this.letter = letter;
        this.quantity = quantity;
        this.isLeaf = true;
    }

    public Node(char letter, int quantity, boolean isLeaf) {
        this.letter = letter;
        this.quantity = quantity;
        this.isLeaf = isLeaf;
    }

    public Node(char letter, int quantity, Node left, Node right) {
        this.letter = letter;
        this.quantity = quantity;
        this.left = left;
        this.right = right;
        this.isLeaf = false;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }

    public char getLetter() {
        return letter;
    }

    public int getQuantity() {
        return quantity;
    }


    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node othernode) {
        return Integer.compare(this.quantity, othernode.getQuantity());
    }
}
