package pl.edu.pw.ee.aisd2023zlab4.performance;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab4.RbtMap;
import pl.edu.pw.ee.aisd2023zlab4.services.MapInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import static java.lang.String.format;

public class RbtMapPerformanceTest {

    private static final Logger LOG = Logger.getLogger(RbtMapPerformanceTest.class.getName());
    private final String filename = "rbtPerformanceResults.txt";

    @Before
    public void setup() {
        removeFileBeforeStart();
    }

    @Test
    public void countTreeHeightDependingOnDataSize() {
        MapInterface<Integer, String> map = new RbtMap<>();
        int currentSize = 0;
        int maxSize = 1_000_000;
        int step = 100;

        int nOfPuts;

        while (currentSize < maxSize) {
            putRandomData(map, step);
            //putAscendingData(map, step, currentSize);
            //putDescendingData(map, step, maxSize - currentSize);
            currentSize += step;

            nOfPuts = getNumOfPuts(map);
            saveResult(currentSize, nOfPuts);
        }

    }

    private void removeFileBeforeStart() {
        File f = new File(filename);

        f.delete();
    }

    private void putRandomData(MapInterface<Integer, String> map, int nOfData) {
        Random rand = new Random();

        for (int i = 0; i < nOfData; i++) {
            int k = rand.nextInt();
            String value = "node" + k;
            map.setValue(k, value);
        }
    }

    private void putAscendingData(MapInterface<Integer, String> map, int nOfData, int currentSize) {
        for (int i = currentSize; i < nOfData + currentSize; i++) {
            String value = "node" + i;
            map.setValue(i, value);
        }
    }

    private void putDescendingData(MapInterface<Integer, String> map, int nOfData, int maxSize) {
        for (int i = maxSize; i > maxSize - nOfData; i--) {
            String value = "node" + i;
            map.setValue(i, value);
        }
    }

    private void saveResult(int currentSize, int nOfPuts) {
        File f = new File(filename);

        try (FileWriter fw = new FileWriter(f, true); BufferedWriter writer = new BufferedWriter(fw)) {
            writer.append(format("size: %4d | nOfPuts: %d\n", currentSize, nOfPuts));

        } catch (IOException e) {
            LOG.severe("Caught an error while saving results.");
            throw new RuntimeException(e);
        }

    }

    private int getNumOfPuts(MapInterface<Integer, String> map) {
        String fieldTree = "tree";
        String fieldNumOfPuts = "currentNumOfPut";

        try {
            Field tree = map.getClass().getDeclaredField(fieldTree);
            tree.setAccessible(true);

            Object rbtTree = tree.get(map);

            Field currentNumOfPut = rbtTree.getClass().getDeclaredField(fieldNumOfPuts);
            currentNumOfPut.setAccessible(true);

            int numOfPuts = currentNumOfPut.getInt(rbtTree);

            return numOfPuts;

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
