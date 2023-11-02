package pl.edu.pw.ee.aisd2023zlab3.utils;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab3.HashOpenAddressing;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedConstructors.createHashInstance;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getNumOfElems;

public class GeneralHashTableTest {
    private final Class<? extends HashOpenAddressing> hashTableClass;
    private HashTable<String> hashStringTable;

    public GeneralHashTableTest(Class<? extends HashOpenAddressing> hashTableClass) {
        this.hashTableClass = hashTableClass;
    }

    @Before
    public void setup() {
        hashStringTable = createHashInstance(hashTableClass);
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        String newEleme = "P. Czarnek";

        // when
        int nOfElemsBeforePut = getNumOfElems(hashStringTable);
        hashStringTable.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(hashStringTable);

        // then
        assertThat(nOfElemsBeforePut).isEqualTo(0);
        assertThat(nOfElemsAfterPut).isEqualTo(1);
    }

    @Test
    public void should_CorrectlyGetValueOfElemFromHashTable() {
        //given
        String elem1 = "value1 I want to get";
        String elem2 = "value2 I want to get";

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
    public void should_ReturnNull_WhenElemIsNotInTheTable() {
        //given
        String elemToFind = "I am not in the table";

        //when
        String value1 = hashStringTable.get(elemToFind);

        //then
        assertThat(value1).isEqualTo(null);
    }
}
