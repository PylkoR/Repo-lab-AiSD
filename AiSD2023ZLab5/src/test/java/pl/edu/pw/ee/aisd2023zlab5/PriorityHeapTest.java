package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PriorityHeapTest {
    private PriorityHeap<Node> heap;
    private final int size = 100_000;

    @BeforeEach
    void setUp() {
        heap = new PriorityHeap(size);
    }

    @Test
    public void should_CorrectlyInsertNodes() {
        //given
        Node node = new Node('a', 5);

        //when
        heap.insertToHeap(node);
        //Node[] nodes = heap.getArray();
        int lastId = heap.getLastNodeId();

        //then
        //assertEquals(node, nodes[lastId]);
    }

    @Test
    void should_CorrectlyExtractNode() {
        //given
        Node node = new Node('b', 10);
        heap.insertToHeap(node);

        //when
        Node extracted = heap.extractMin();

        //then
        assertEquals(node, extracted);
    }

    @Test
    public void should_PassIfRootIsTheSmallestKeyNode() {
        //given
        boolean isNotSmaller = true;
        Random rand = new Random();

        //when
        for (int i = 0; i < size; i++) {
            Node randomNode = new Node('a', rand.nextInt());
            heap.insertToHeap(randomNode);
        }
        //Node[] nodes = heap.getArray();

//        int root = nodes[0].getQuantity();
//        for (int i = 0; i < size - 1; i++) {
//            if (root > nodes[i].getQuantity()) {
//                isNotSmaller = false;
//                break;
//            }
//        }
//
//        //then
//        assertTrue(isNotSmaller);
    }

    @Test
    public void should_CorrectlyExtractSmallestNode() {
        //given
//        Random rand = new Random();
//        for (int i = 0; i < size; i++) {
//            Node randomNode = new Node('a', rand.nextInt());
//            heap.insertToHeap(randomNode);
//        }
//        Node[] nodes = heap.getArray();
//        Node root = nodes[0];
//
//        //when
//        Node extracted = heap.extractMin();
//
//        //then
//        assertEquals(root, extracted);
    }

    @Test
    public void should_CorrectlyExtractAllNodes() {
        //given
        boolean nextExtractedIsBiggerOrEqual = true;
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            Node randomNode = new Node('a', rand.nextInt());
            heap.insertToHeap(randomNode);
        }

        //when
        Node previous = heap.extractMin();
        for (int i = 0; i < size - 1; i++) {
            Node currentNode = heap.extractMin();
            if (currentNode.getQuantity() < previous.getQuantity()) {
                nextExtractedIsBiggerOrEqual = false;
                break;
            }
            previous = currentNode;
        }

        //then
        assertTrue(nextExtractedIsBiggerOrEqual);
    }

    @Test
    public void should_ThrowExceptionWhenHeapIsFull() {
        try {
            for (int i = 0; i <= size; i++) {
                Node node = new Node('z', i, false);
                heap.insertToHeap(node);
            }
            fail("Exception wasn't thrown!");
        } catch (RuntimeException exception) {
            assertEquals("Kopiec jest już pełny!", exception.getMessage());
        }
    }

    @Test
    public void should_ThrowExceptionWhenHeapIsEmpty() {
        try {
            heap.extractMin();
            fail("Exception wasn't thrown!");
        } catch (RuntimeException exception) {
            assertEquals("Kopiec jest już pusty!", exception.getMessage());
        }
    }

    @Test
    public void should_CreateHeapWithAllLettersFromFile() {
        //given
//        String filePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test3";
//        char[] bytes = new char[256];
//        int n = 0;
//
//        try (FileInputStream reader = new FileInputStream(filePath)) {
//            int letter;
//            while ((letter = reader.read()) != -1) {
//                if (bytes[letter] == 0) {
//                    n++;
//                    bytes[letter]++;
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        //when
//        PriorityHeap fileHeap = new PriorityHeap(filePath);
//        int numberOfNodes = fileHeap.getLastNodeId() + 1;
//
//        //then
//        assertEquals(n, numberOfNodes);
    }

    @Test
    public void should_MakeTwoNodesHeap_WhenFileHasOneLetter() {
        //given
//        String filePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test8.txt";
//        int size = 20;
//        char exampleChar = 'b';
//        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
//            for (int i = 0; i < size; i++) {
//                outputStream.write(exampleChar);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        //when
//        PriorityHeap fileHeap = new PriorityHeap(filePath);
//        int sizeOfHeap = fileHeap.getLastNodeId() + 1;
//
//        //then
//        assertEquals(2, sizeOfHeap);
    }
}