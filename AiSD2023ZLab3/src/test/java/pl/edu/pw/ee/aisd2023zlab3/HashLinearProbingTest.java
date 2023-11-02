package pl.edu.pw.ee.aisd2023zlab3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab3.utils.GeneralHashTableTest;

public class HashLinearProbingTest extends GeneralHashTableTest {

    public HashLinearProbingTest() {
        super(HashLinearProbing.class);
    }

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
}
