package pl.edu.pw.ee.aisd2023zlab1;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import static java.util.Objects.isNull;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        validateParams(nums);

        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            double tmp = nums[i];

            while (j >= 0 && tmp < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = tmp;
        }
    }

    private void validateParams(double[] nums) {
        if (isNull(nums)) {
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }

}
