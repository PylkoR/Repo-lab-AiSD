package pl.edu.pw.ee.aisd2023zlab1;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import static pl.edu.pw.ee.aisd2023zlab1.services.SortingUtils.*;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        validateParams(nums);

        int n = nums.length;

        int minValid;

        for (int i = 0; i < n - 1; i++) {
            minValid = i;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minValid]) {
                    minValid = j;
                }
            }
            swap(nums, minValid, i);
        }
    }
}
