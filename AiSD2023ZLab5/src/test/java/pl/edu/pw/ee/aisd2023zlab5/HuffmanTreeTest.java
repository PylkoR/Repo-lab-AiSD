package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTreeTest {
    @Test
    void should_CreateHufTreeWithCorrectCodes_WhenLettersHaveDifferentQuantities(){
//        //given
//
//
//        //when
//        HuffmanTree tree = new HuffmanTree("src/testFiles/test2");
//
//
//        //then
    }

    @Test
    public void testEmptyFile() {
        try {
            HuffmanTree huffmanTree = new HuffmanTree("src/testFiles/empty.txt");
            fail("Exception wasn't thrown!");
        } catch (IllegalArgumentException exception) {
            assertEquals("Plik jest pusty!", exception.getMessage());
        }
    }

}