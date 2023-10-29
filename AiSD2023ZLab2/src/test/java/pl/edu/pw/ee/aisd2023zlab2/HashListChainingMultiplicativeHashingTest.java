package pl.edu.pw.ee.aisd2023zlab2;

import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab2.services.HashTable;
import pl.edu.pw.ee.aisd2023zlab2.utils.GeneralHashListChainingTest;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.edu.pw.ee.aisd2023zlab2.utils.AdvancedGetters.getHashElemById;
import static pl.edu.pw.ee.aisd2023zlab2.utils.AdvancedGetters.getNumOfElems;

public class HashListChainingMultiplicativeHashingTest  extends GeneralHashListChainingTest {
    public HashListChainingMultiplicativeHashingTest(){
        super(HashListChainingMultiplicativeHashing.class);
    }

    @Test
    public void should_CorrectlyAddThreeDifferentElems_WhenHashSizeEqualOne() {
        // given
        int hashSize = 1;
        HashTable<String> names = new HashListChainingMultiplicativeHashing<>(hashSize);
        names.add("Ola");
        names.add("Ala");
        names.add("Ula");

        // when
        int nOfElemsInHash = getNumOfElems(names);
        String firstName = getHashElemById(names, 0);
        String secondName = getHashElemById(names, 1);
        String thirdName = getHashElemById(names, 2);

        // then
        assertThat(nOfElemsInHash).isEqualTo(3);
        assertThat(firstName).isEqualTo("Ula");
        assertThat(secondName).isEqualTo("Ala");
        assertThat(thirdName).isEqualTo("Ola");
    }

    @Test
    public void should_CorrectlyAddAndRemoveThreeDifferentElems_WhenHashSizeEqualOne() {
        // given
        int hashSize = 1;
        HashTable<String> names = new HashListChainingMultiplicativeHashing<>(hashSize);
        names.add("Ola");
        names.add("Ala");
        names.add("Ula");

        // when
        int nOfElemsInHash = getNumOfElems(names);
        names.delete("Ola");
        names.delete("Ula");
        String onlyName = names.get("Ala");
        int nOfElemsInHashAfterRemoving = getNumOfElems(names);

        // then
        assertThat(nOfElemsInHash).isEqualTo(3);
        assertThat(nOfElemsInHashAfterRemoving).isEqualTo(1);
        assertThat(onlyName).isEqualTo("Ala");
    }
}
