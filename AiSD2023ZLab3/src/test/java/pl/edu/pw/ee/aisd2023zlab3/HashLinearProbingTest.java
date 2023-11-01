package pl.edu.pw.ee.aisd2023zlab3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;
import static pl.edu.pw.ee.aisd2023zlab3.utils.AdvancedGetters.getNumOfElems;

public class HashLinearProbingTest {

    @Test
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            new HashLinearProbing<>(initialSize);
        });

        // then
        String message = "Initial size of hash table cannot be lower than 1!";

        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> unusedNames = new HashLinearProbing<>();
        String newEleme = "P. Czarnek";

        // when
        int nOfElemsBeforePut = getNumOfElems(unusedNames);
        unusedNames.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(unusedNames);

        // then
        assertThat(nOfElemsBeforePut).isEqualTo(0);
        assertThat(nOfElemsAfterPut).isEqualTo(1);
    }

    @Test
    public void should_CorrectlyGetValueOfElemFromHashTable(){
        //given
        HashTable<String> valuesToGet = new HashLinearProbing<>();
        String elem1 = "value1 I want to get";
        String elem2 = "value2 I want to get";

        //when
        valuesToGet.put(elem1);
        valuesToGet.put(elem2);

        String value1 = valuesToGet.get(elem1);
        String value2 = valuesToGet.get(elem2);

        //then
        assertThat(value1).isEqualTo(elem1);
        assertThat(value2).isEqualTo(elem2);
    }

    @Test
    public void should_ReturnNull_WhenElemIsNotInTheTable(){
        //given
        HashTable<String> valuesToGet = new HashLinearProbing<>();
        String elemToFind = "Not in the table";

        //when
        String value1 = valuesToGet.get(elemToFind);

        //then
        assertThat(value1).isEqualTo(null);
    }
}
