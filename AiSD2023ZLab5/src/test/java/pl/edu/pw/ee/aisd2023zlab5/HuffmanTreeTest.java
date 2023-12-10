package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTreeTest {
    @Test
    public void should_CreateHufTreeWithCorrectCodes_WhenLettersHaveDifferentQuantities() {
        //given
        int numberOfa = 10;
        int numberOfc = 20;
        int numberOfe = 5;
        int numberOfk = 7;
        int numberOfh = 22;

        try (FileOutputStream output = new FileOutputStream("src/testFiles/test8.txt")) {
            for (int i = 0; i < numberOfh; i++) {
                if (i <= numberOfk) output.write('k');
                if (i <= numberOfe) output.write('e');
                if (i <= numberOfc) output.write('c');
                if (i <= numberOfa) output.write('a');
                output.write('h');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //when
        HuffmanTree tree = new HuffmanTree("src/testFiles/test8.txt");
        String[] codes = tree.getCodes();
        char mostCommonLetter = 0;
        int e = 'e';
        int shortestCode = codes[e].length();

        for (int i = 0; i < 256; i++) {
            if (codes[i] != null && codes[i].length() < shortestCode) {
                mostCommonLetter = (char) i;
            }
        }

        //then
        assertEquals(mostCommonLetter, 'h');
    }

    @Test
    public void should_ThrowException_WhenFileIsEmpty() {
        try {
            HuffmanTree huffmanTree = new HuffmanTree("src/testFiles/empty.txt");
            fail("Exception wasn't thrown!");
        } catch (IllegalArgumentException exception) {
            assertEquals("Plik jest pusty!", exception.getMessage());
        }
    }

    @Test
    public void should_FindCorrectLetterInTreeByCode() {
        //given
        int size = 256;
        try (FileOutputStream outputStream = new FileOutputStream("src/testFiles/test8.txt")) {
            Random rand = new Random();
            for (int i = 0; i < size; i++) {
                char randChar = (char) rand.nextInt(128);
                outputStream.write(randChar);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //when
        HuffmanTree tree = new HuffmanTree("src/testFiles/test8.txt");
        Node root = tree.getRoot();
        String[] codes = tree.getCodes();

        for (int i = 0; i < codes.length; i++) {
            if (codes[i] != null) {
                Node currentNode = root;
                String code = codes[i];

                for (int j = 0; j < code.length(); j++) {
                    if (code.charAt(j) == '1') {
                        currentNode = currentNode.getRight();
                    } else {
                        currentNode = currentNode.getLeft();
                    }
                }
                assertEquals(currentNode.getLetter(), (char) i);
            }
        }
    }

}