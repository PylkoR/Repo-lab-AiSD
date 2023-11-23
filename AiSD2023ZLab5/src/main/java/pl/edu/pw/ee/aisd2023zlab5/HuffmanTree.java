package pl.edu.pw.ee.aisd2023zlab5;

public class HuffmanTree {
    Node root;

    public HuffmanTree(String fileName) {
        this.root = createHuffmanTree(fileName);
    }

    private Node createHuffmanTree(String fileName){
        PriorityHeap heap = new PriorityHeap(fileName);
        for (int i = 0; i <= heap.getSize(); i++){
            Node tmp = heap.extractMin();
            Node tmp2 = heap.extractMin();
            System.out.println(tmp.getQuantity() + " " + tmp2.getQuantity());
            Node newNode = new Node((char) 0, tmp.getQuantity() + tmp2.getQuantity(), tmp, tmp2);

            heap.insertToHeap(newNode);
        }

        return heap.getArray()[0];
    }

    public Node getRoot(){
        return root;
    }
}
