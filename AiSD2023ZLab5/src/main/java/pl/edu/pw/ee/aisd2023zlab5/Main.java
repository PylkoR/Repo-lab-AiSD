package pl.edu.pw.ee.aisd2023zlab5;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("""
                    Two run arguments are needed:
                    1 - path to file that you want compress or decompress
                    2 - path to directory where you want to save compressed or decompressed file""");
        }
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        int i = inputFile.getName().lastIndexOf('.');
        String name;
        String extension = "";
        if (i == -1) {
            name = inputFile.getName();
        } else {
            name = inputFile.getName().substring(0, i);
            extension = inputFile.getName().substring(i);
        }

        String decompressedFilePath = outputFile.getAbsolutePath() + "\\" + name + ".txt";
        String compressedFilePath = outputFile.getAbsolutePath() + "\\" + name + ".huf";

        if (extension.equals(".huf")) {
            Decompressor decompressor = new Decompressor();
            decompressor.decompress(args[0], decompressedFilePath);
        } else {
            Compressor compressor = new Compressor();
            compressor.compress(args[0], compressedFilePath);
        }
    }
}
