package pl.edu.pw.ee.aisd2023zlab1.utils;

import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public abstract class GeneralSortTest {

    protected Sorting sorter;

    public GeneralSortTest(Sorting sorter) {
        this.sorter = sorter;
    }

    @Test
    public void should_ThrowException_WhenInputIsNull() {
        // given
        double[] nums = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            sorter.sort(nums);
        });

        // then
        String message = "Input args (nums) cannot be null!";

        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ReturnEmptyArray_WhenInputIsEmpty() {
        // given
        double[] nums = {};

        // when
        sorter.sort(nums);

        // then
        assertThat(nums).isEmpty();
    }

    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsRandomAndHuge() {
        // given
        int size = 10_000;
        double[] nums = createRandomData(size);
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    @Test
    public void should_CorrectlySort_WhenInputIsSorted() {
        //given
        int size = 10_000;
        double[] nums = createRandomData(size);
        double[] numsCopy = nums.clone();

        Arrays.sort(nums);

        //when
        sorter.sort(nums);

        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    @Test
    public void should_CorrectlySort_WhenInputIsSortedAndReversed() {
        //given
        int size = 10_000;
        double[] nums = createRandomData(size);
        double[] numsCopy = nums.clone();

        Arrays.sort(nums);

        int p = 0;
        int k = size - 1;
        while (p < k) {
            double tmp = nums[p];

            nums[p] = nums[k];
            nums[k] = tmp;
            p++;
            k--;
        }

        //when
        sorter.sort(nums);

        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    @Test
    public void should_CorrectlySort_WhenInputTableContainsOneNumber() {
        //given
        int size = 10_000;
        double[] nums = new double[size];
        Random rand = new Random();

        Arrays.fill(nums, rand.nextDouble());
        double[] numsCopy = nums.clone();

        //when
        sorter.sort(nums);

        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    @Test
    public void should_CorrectlyAscendingSortAndPrintResults_WhenInputIsRandomAndSmall() {
        // given
        int size = 10;
        double[] nums = createRandomDataWithoutSeed(size);
        double[] numsCopy = nums.clone();
        System.out.println("Przed sortowniem: " + Arrays.toString(nums));

        // when
        sorter.sort(nums);
        System.out.println("Po sortowniu: " + Arrays.toString(nums));

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    @Test
    public void should_CorrectlySort_WhenTableHaveOneElement(){
        //given
        double[] nums = new double[1];
        Random rand = new Random();
        nums[0] = rand.nextDouble();

        double[] numsCopy = nums.clone();

        //when
        sorter.sort(nums);

        //then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    private double[] createRandomData(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        long eliteSeed = 31337;
        Random rand = new Random(eliteSeed);

        for (int i = 0; i < size; i++) {
            nums[i] = rand.nextDouble();
        }

        return nums;
    }

    private double[] createRandomDataWithoutSeed(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            nums[i] = rand.nextDouble();
        }

        return nums;
    }
}
