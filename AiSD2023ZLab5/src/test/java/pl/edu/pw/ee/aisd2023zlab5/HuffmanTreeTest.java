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
        String testFilePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test8.txt";
        int numberOfa = 10;
        int numberOfc = 20;
        int numberOfe = 5;
        int numberOfk = 7;
        int numberOfh = 22;

        try (FileOutputStream output = new FileOutputStream(testFilePath)) {
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
        HuffmanTree tree = new HuffmanTree(testFilePath);
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
        //given
        String testFilePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/empty.txt";

        //when
        try {
            HuffmanTree huffmanTree = new HuffmanTree(testFilePath);
            fail("Exception wasn't thrown!");
        } catch (IllegalArgumentException exception) {
            assertEquals("Plik jest pusty!", exception.getMessage());
        }
    }

    @Test
    public void should_FindCorrectLetterInTreeByCode() {
        //given
        String testFilePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test8.txt";
        int size = 256;
        try (FileOutputStream outputStream = new FileOutputStream(testFilePath)) {
            Random rand = new Random();
            for (int i = 0; i < size; i++) {
                char randChar = (char) rand.nextInt(128);
                outputStream.write(randChar);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //when
        HuffmanTree tree = new HuffmanTree(testFilePath);
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

    @Test
    public void should_CreateThreeNodeTreeForOneLetterFile() {
        //given
        String testFilePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test8.txt";
        char exampleChar = 'w';
        try (FileOutputStream outputStream = new FileOutputStream(testFilePath)) {
            for (int i = 0; i < 4; i++) {
                outputStream.write(exampleChar);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //when
        HuffmanTree tree = new HuffmanTree(testFilePath);
        String[] codes = tree.getCodes();
        Node root = tree.getRoot();
        Node leftSon = root.getLeft();
        Node rightSon = root.getRight();

        //then
        assertNotNull(codes[exampleChar]);
        assertEquals(exampleChar, rightSon.getLetter());
        assertEquals((char) 0, leftSon.getLetter());
    }
}