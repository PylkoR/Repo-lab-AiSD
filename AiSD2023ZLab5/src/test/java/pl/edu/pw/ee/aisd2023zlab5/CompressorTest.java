package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CompressorTest {
    @Test
    public void should_ThrowException_WhenFileIsEmpty() {
        //given
        String testFilePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/empty.txt";

        //when
        try {
            Compressor comp = new Compressor();
            comp.compress(testFilePath, "src/CompressedFiles");
            fail("Exception wasn't thrown!");
        } catch (IllegalArgumentException exception) {
            assertEquals("Plik jest pusty!", exception.getMessage());
        }
    }

    @Test
    void should_ThrowException_WhenFileIsBelowThreeBytes() {
        //given
        String testFilePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test6";

        //when
        try {
            Compressor comp = new Compressor();
            comp.compress(testFilePath, "src/CompressedFiles");
            fail("Exception wasn't thrown!");
        } catch (IllegalArgumentException exception) {
            assertEquals("File is too small(below 3 bytes).", exception.getMessage());
        }
    }

    @Test
    void should_WriteZeroOnFirstThreeBits_WhenFilledZero() {
        //given
        int threeBits = 0b1110_0000;
        int firstByte;
        String testFilePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test0.txt";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/test.huf";

        //when
        Compressor comp = new Compressor();
        comp.compress(testFilePath, outputPath);

        try (FileInputStream reader = new FileInputStream(outputPath)) {
            firstByte = reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        threeBits = firstByte & threeBits;

        //then
        assertEquals(0, threeBits);
    }

    @Test
    void should_CompressCorrectly_WhenFilledIsNotZero() {
        int threeBits = 0b1110_0000;
        int filledExpected = 0b1100_0000;
        int firstByte;
        String testFilePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test5";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/test.huf";

        //when
        Compressor comp = new Compressor();
        comp.compress(testFilePath, outputPath);

        try (FileInputStream reader = new FileInputStream(outputPath)) {
            firstByte = reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        threeBits = firstByte & threeBits;

        //then
        assertEquals(filledExpected, threeBits);
    }

    @Test
    void should_CorrectlyCompressFewFiles() {
        //given
        String inputOne = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/Romeo.txt";
        String inputTwo = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test1";
        String inputThree = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test2";
        String inputFour = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test3";
        String inputFive = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test4.txt";
        String inputSix = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test5";
        String inputSeven = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test0.txt";

        String outputDir = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/";


        //when
        Compressor comp = new Compressor();
        comp.compress(inputOne, outputDir + "Romeo.huf");
        comp.compress(inputTwo, outputDir + "test1.huf");
        comp.compress(inputThree, outputDir + "test2.huf");
        comp.compress(inputFour, outputDir + "test3.huf");
        comp.compress(inputFive, outputDir + "test4.huf");
        comp.compress(inputSix, outputDir + "test5.huf");
        comp.compress(inputSeven, outputDir + "test0.huf");

        //w testach dekompresora dekoduje te pliki, jeżeli wyniki będą identyczne jak pliki wejściowe do kompresji,
        // to uznaje, że kompresja i dekompresja działają poprawnie
    }
}