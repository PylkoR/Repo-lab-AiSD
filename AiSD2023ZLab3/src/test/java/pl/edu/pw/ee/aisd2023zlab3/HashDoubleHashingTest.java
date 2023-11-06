package pl.edu.pw.ee.aisd2023zlab3;

import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;
import pl.edu.pw.ee.aisd2023zlab3.utils.GeneralHashTableTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getNumOfElems;

public class HashDoubleHashingTest extends GeneralHashTableTest {
    public HashDoubleHashingTest() {
        super(HashDoubleHashing.class);
    }

    @Test
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            new HashDoubleHashing<>(initialSize);
        });

        // then
        String message = "Initial size of hash table cannot be lower than 1!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_correctlyAddElems_whenInitialSizeIs1() {
        //given
        int initialSize = 1;
        HashTable<String> hashStringTable = new HashDoubleHashing<>(initialSize);
        String elem1 = "First value";
        String elem2 = "Second value";

        //when
        hashStringTable.put(elem1);
        hashStringTable.put(elem2);
        int nOfElemsAfterPut = getNumOfElems(hashStringTable);

        //then
        assertThat(nOfElemsAfterPut).isEqualTo(2);
    }

    @Test
    public void should_correctlyGetElems_whenInitialSizeIs1() {
        //given
        int initialSize = 1;
        HashTable<String> hashStringTable = new HashDoubleHashing<>(initialSize);
        String elem1 = "First value";
        String elem2 = "Second value";

        //when
        hashStringTable.put(elem1);
        hashStringTable.put(elem2);

        String value1 = hashStringTable.get(elem1);
        String value2 = hashStringTable.get(elem2);

        //then
        assertThat(value1).isEqualTo(elem1);
        assertThat(value2).isEqualTo(elem2);
    }

    @Test
    public void should_correctlyDeleteElems_whenInitialSizeIs1() {
        //given
        int initialSize = 1;
        HashTable<String> hashStringTable = new HashDoubleHashing<>(initialSize);
        String elem1 = "First value";
        String elem2 = "Second value";

        //when
        hashStringTable.put(elem1);
        hashStringTable.put(elem2);
        int nOfElemsAfterPut = getNumOfElems(hashStringTable);

        hashStringTable.delete(elem1);
        int nOfElemsAfterDelete1 = getNumOfElems(hashStringTable);

        hashStringTable.delete(elem2);
        int nOfElemsAfterDelete2 = getNumOfElems(hashStringTable);

        //then
        assertThat(nOfElemsAfterPut).isEqualTo(2);
        assertThat(nOfElemsAfterDelete1).isEqualTo(1);
        assertThat(nOfElemsAfterDelete2).isEqualTo(0);
    }

    @Test
    public void shouldNot_DeleteElems_whenInitialSizeIs1AndElemIsNotInTheTable() {
        //given
        int initialSize = 1;
        HashTable<String> hashStringTable = new HashDoubleHashing<>(initialSize);
        String elem1 = "I am not in the table";
        String elem2 = "I am in the table";

        //when
        hashStringTable.put(elem2);
        int nOfElemsAfterPut = getNumOfElems(hashStringTable);

        hashStringTable.delete(elem1);
        int nOfElemsAfterDelete = getNumOfElems(hashStringTable);

        //then
        assertThat(nOfElemsAfterDelete).isEqualTo(nOfElemsAfterPut);

    }
}
