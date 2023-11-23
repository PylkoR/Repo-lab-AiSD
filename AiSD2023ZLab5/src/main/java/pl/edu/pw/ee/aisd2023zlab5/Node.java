package pl.edu.pw.ee.aisd2023zlab5;

public class Node {
    private char letter;
    private int quantity;
    private Node left;
    private Node right;

    public void Node(char letter, int quantity){
        this.letter = letter;
        this.quantity = quantity;
    }

    public void Node(char letter, int quantity, Node left, Node right){
        this.letter = letter;
        this.quantity = quantity;
        this.left = left;
        this.right = right;
    }
}
