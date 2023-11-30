package pl.edu.pw.ee.aisd2023zlab5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Compressor {

    public void compress(String fileName){
        HuffmanTree tree = new HuffmanTree(fileName);
        String[] codes = tree.getCodes();

        try (FileInputStream fileReader = new FileInputStream(fileName)) {
            int asciiSign;
            String outputFile = fileName + ".h";

            try (FileOutputStream fileOutput = new FileOutputStream(outputFile)) {
                int toWrite = 0;
                int filled = 3;

                //TODO kompresja drzewa

                while ((asciiSign = fileReader.read()) != -1) {
                    String code = codes[asciiSign];

                    for (int i = 0; i < code.length(); i++){
                        char currentChar = code.charAt(i);

                        toWrite = toWrite << 1;
                        if ( currentChar == '1'){
                            toWrite++;
                        }
                        filled++;

                        if (filled == 8){
                            fileOutput.write(toWrite);
                            toWrite = 0;
                            filled = 0;
                        }
                    }
                }
                if (filled != 0) {
                    toWrite = toWrite << 8 - filled;
                    fileOutput.write(toWrite);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
