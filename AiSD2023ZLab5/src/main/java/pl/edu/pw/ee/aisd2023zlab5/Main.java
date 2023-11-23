package pl.edu.pw.ee.aisd2023zlab5;

public class Main {
    public static void main(String[] args) {
        PriorityHeap heap = new PriorityHeap(args[0]);
        Node[] heapNodes = heap.getArray();

        for (int i = 0; i < heapNodes.length; i++) {
            System.out.println(heapNodes[i].getLetter() + " " + heapNodes[i].getQuantity());
        }

        System.out.println(" ");
        Node min = heap.extractMin();
        Node min1 = heap.extractMin();
        Node min2 = heap.extractMin();
        Node min3 = heap.extractMin();
        Node min4 = heap.extractMin();
        Node min5 = heap.extractMin();
        Node min6 = heap.extractMin();
        System.out.println(" ");

        System.out.println(min.getLetter() + " " + min.getQuantity());
        System.out.println(min1.getLetter() + " " + min1.getQuantity());
        System.out.println(min2.getLetter() + " " + min2.getQuantity());
        System.out.println(min3.getLetter() + " " + min3.getQuantity());
        System.out.println(min4.getLetter() + " " + min4.getQuantity());
        System.out.println(min5.getLetter() + " " + min5.getQuantity());
        System.out.println(min6.getLetter() + " " + min6.getQuantity());

        System.out.println(" ");


        for (int i = 0; i < heapNodes.length; i++) {
            System.out.println(heapNodes[i].getLetter() + " " + heapNodes[i].getQuantity());
        }

        System.out.println("\n");

        HuffmanTree tree = new HuffmanTree(args[0]);
        Node huffman = tree.getRoot();

        int tmp = huffman.getQuantity();
        System.out.println(tmp);

    }
}
