package pl.edu.pw.ee.aisd2023zlab1;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import static pl.edu.pw.ee.aisd2023zlab1.services.SortingUtils.*;

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

    public void sort(double[] nums, int left, int right) {
        validateParams(nums);

        for (int i = left + 1; i < right + 1; i++) {
            int j = i - 1;
            double tmp = nums[i];

            while (j >= 0 && tmp < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = tmp;
        }
    }
}
