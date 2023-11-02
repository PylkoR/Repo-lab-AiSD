package pl.edu.pw.ee.aisd2023zlab3;

import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab3.utils.GeneralHashTableTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class HashDoubleHashingTest extends GeneralHashTableTest {
    public HashDoubleHashingTest(){
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
}
