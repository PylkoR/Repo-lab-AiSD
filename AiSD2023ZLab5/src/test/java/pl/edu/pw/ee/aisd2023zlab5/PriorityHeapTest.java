package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityHeapTest {
    private PriorityHeap heap;

    @BeforeEach
    void setUp() {
        heap = new PriorityHeap(5);
    }

    @Test
    public void should_CorrectlyInsertNodes(){
        //given
        Node node = new Node('a',5);

        //when
        heap.insertToHeap(node);

        Node firstExtracted = heap.extractMin();

        //then
        assertEquals(node, firstExtracted);
    }
}