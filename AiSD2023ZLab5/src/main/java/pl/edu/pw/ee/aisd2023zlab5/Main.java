package pl.edu.pw.ee.aisd2023zlab5;

public class Main {
    public static void main(String[] args) {
        PriorityHeap heap = new PriorityHeap(args[0]);
        Node[] heapNodes = heap.getArray();

        for (Node heapNode : heapNodes) {
            System.out.println(heapNode.getLetter() + " " + heapNode.getQuantity());
        }
        System.out.println("wezly po odczycie");

        for (int i = 0; i < heapNodes.length; i++) {
            Node min = heap.extractMin();
            System.out.println(min.getLetter() + " " + min.getQuantity());
        }
        System.out.println("wyjęte z kopca");

        for (Node heapNode : heapNodes) {
            System.out.println(heapNode.getLetter() + " " + heapNode.getQuantity());
        }
        System.out.println("kopiec po wyjęciu węzłów");

        System.out.println("\nDrzewo");

        HuffmanTree tree = new HuffmanTree(args[0]);
        Node huffman = tree.getRoot();

        int tmp = huffman.getQuantity();
        System.out.println("korzen " + tmp + "\n");

        String[] codes = tree.getCodes();
        for (int i = 0; i < codes.length; i++) {
            if (codes[i] != null) {
                System.out.println((char) i + " - " + codes[i]);
            }
        }

        //KOMPRESJA
        Compressor compressor = new Compressor();
        compressor.compress(args[0]);

        System.out.println("\nDekompresja");

        //DEKOMPRESJA
        Decompressor decompressor = new Decompressor();
        decompressor.decompress(args[0] + ".huf");

    }
}
