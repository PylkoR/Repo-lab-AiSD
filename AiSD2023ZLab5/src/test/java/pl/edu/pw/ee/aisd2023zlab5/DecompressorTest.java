package pl.edu.pw.ee.aisd2023zlab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DecompressorTest {
    Decompressor decomp;

    @BeforeEach
    public void setUp() {
        decomp = new Decompressor();
    }

    @Test
    public void should_ThrowException_WhenFileIsEmpty() {
        //given
        String testFilePath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/empty.huf";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/DecompressedFiles/empty.txt";

        try {
            decomp.decompress(testFilePath, outputPath);
            fail("Exception wasn't thrown!");
        } catch (IllegalArgumentException exception) {
            assertEquals("Plik jest pusty!", exception.getMessage());
        }
    }

    @Test
    void should_CorrectlyDecompressSmallFile() {
        //given
        String inputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/test1.huf";
        String beforeCompressionPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test1";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/DecompressedFiles/" + "test1.txt";

        //when
        decomp.decompress(inputPath, outputPath);

        Path pathBefore = Paths.get(beforeCompressionPath);
        Path pathAfter = Paths.get(outputPath);

        String fileBefore;
        String fileAfter;
        try {
            fileBefore = Files.readString(pathBefore);
            fileAfter = Files.readString(pathAfter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        assertEquals(fileBefore, fileAfter);
    }

    @Test
    void should_CorrectlyDecompressBigFile() {
        //given
        String inputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/Romeo.huf";
        String beforeCompressionPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/Romeo.txt";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/DecompressedFiles/" + "Romeo.txt";

        //when
        decomp.decompress(inputPath, outputPath);

        Path pathBefore = Paths.get(beforeCompressionPath);
        Path pathAfter = Paths.get(outputPath);

        String fileBefore;
        String fileAfter;
        try {
            fileBefore = Files.readString(pathBefore);
            fileAfter = Files.readString(pathAfter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        assertEquals(fileBefore, fileAfter);
    }

    @Test
    void should_CorrectlyDecompressFileWithOneLetterCoded() {
        //given
        String inputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/test0.huf";
        String beforeCompressionPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test0.txt";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/DecompressedFiles/" + "test0.txt";

        //when
        decomp.decompress(inputPath, outputPath);

        Path pathBefore = Paths.get(beforeCompressionPath);
        Path pathAfter = Paths.get(outputPath);

        String fileBefore;
        String fileAfter;
        try {
            fileBefore = Files.readString(pathBefore);
            fileAfter = Files.readString(pathAfter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        assertEquals(fileBefore, fileAfter);
    }

    @Test
    void should_CorrectlyDecompressTest2File() {
        //given
        String inputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/test2.huf";
        String beforeCompressionPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test2";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/DecompressedFiles/" + "test2.txt";

        //when
        decomp.decompress(inputPath, outputPath);

        Path pathBefore = Paths.get(beforeCompressionPath);
        Path pathAfter = Paths.get(outputPath);

        String fileBefore;
        String fileAfter;
        try {
            fileBefore = Files.readString(pathBefore);
            fileAfter = Files.readString(pathAfter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        assertEquals(fileBefore, fileAfter);
    }

    @Test
    void should_CorrectlyDecompressTest3File() {
        //given
        String inputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/test3.huf";
        String beforeCompressionPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test3";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/DecompressedFiles/" + "test3.txt";

        //when
        decomp.decompress(inputPath, outputPath);

        Path pathBefore = Paths.get(beforeCompressionPath);
        Path pathAfter = Paths.get(outputPath);

        String fileBefore;
        String fileAfter;
        try {
            fileBefore = Files.readString(pathBefore);
            fileAfter = Files.readString(pathAfter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        assertEquals(fileBefore, fileAfter);
    }

    @Test
    void should_CorrectlyDecompressTest4File() {
        //given
        String inputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/test4.huf";
        String beforeCompressionPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test4.txt";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/DecompressedFiles/" + "test4.txt";

        //when
        decomp.decompress(inputPath, outputPath);

        Path pathBefore = Paths.get(beforeCompressionPath);
        Path pathAfter = Paths.get(outputPath);

        String fileBefore;
        String fileAfter;
        try {
            fileBefore = Files.readString(pathBefore);
            fileAfter = Files.readString(pathAfter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        assertEquals(fileBefore, fileAfter);
    }

    @Test
    void should_CorrectlyDecompressTest5File() {
        //given
        String inputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/CompressedFiles/test5.huf";
        String beforeCompressionPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/testFiles/test5";
        String outputPath = "src/test/java/pl/edu/pw/ee/aisd2023zlab5/DecompressedFiles/" + "test5.txt";

        //when
        decomp.decompress(inputPath, outputPath);

        Path pathBefore = Paths.get(beforeCompressionPath);
        Path pathAfter = Paths.get(outputPath);

        String fileBefore;
        String fileAfter;
        try {
            fileBefore = Files.readString(pathBefore);
            fileAfter = Files.readString(pathAfter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        assertEquals(fileBefore, fileAfter);
    }
}