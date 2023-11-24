package pl.edu.pw.ee.aisd2023zlab5;

import java.util.HashMap;

public class HuffmanTree {
    Node root;
    private HashMap<Character,String> codes;

    public HuffmanTree(String fileName) {
        this.root = createHuffmanTree(fileName);
        this.codes = new HashMap<>();
    }

    private Node createHuffmanTree(String fileName) {
        PriorityHeap heap = new PriorityHeap(fileName);
        while (heap.getSize() > 0) {
            Node tmp = heap.extractMin();
            Node tmp2 = heap.extractMin();
            System.out.println(tmp.getLetter() + "->" + tmp.getQuantity() + " + " + tmp2.getLetter() + "->" + tmp2.getQuantity() + "=" + (Integer)(tmp.getQuantity() + tmp2.getQuantity()));
            Node newNode = new Node((char) 0, tmp.getQuantity() + tmp2.getQuantity(), tmp, tmp2);

            heap.insertToHeap(newNode);
        }

        return heap.getArray()[0];
    }

    public Node getRoot() {
        return root;
    }


    private void traverseTree(Node node,String code,String param)
    {
        code+=param;
        if(node.isLeaf())
        {
            this.codes.put((Character)node.getLetter(), code);
            return;
        }
        traverseTree(node.getLeft(),code,"0");
        traverseTree(node.getRight(),code,"1");
    }

    public void createCodes()
    {
        traverseTree(this.getRoot(),"","");
    }
    public HashMap<Character,String> getCodes(){return this.codes;}


}
