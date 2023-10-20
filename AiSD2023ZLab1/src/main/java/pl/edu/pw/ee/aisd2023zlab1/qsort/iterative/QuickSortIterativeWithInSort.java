package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

import pl.edu.pw.ee.aisd2023zlab1.InsertionSort;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.pw.ee.aisd2023zlab1.services.SortingUtils.*;

public class QuickSortIterativeWithInSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        validateParams(nums);

        quicksort(nums);
    }

    private void quicksort(double[] data) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        Integer left = 0;
        Integer right = data.length - 1;

        starts.add(left);
        ends.add(right);

        int n = 1;
        int pivot;

        if (left < right) {

            while (n > 0) {
                n--;
                left = starts.remove(n);
                right = ends.remove(n);

                if (right - left < 36) {
                    InsertionSort insert = new InsertionSort();
                    insert.sort(data, left, right);
                    continue;
                }
                pivot = partition(data, left, right);

                if (pivot > left) {
                    starts.add(left);
                    ends.add(pivot);
                    n++;
                }

                if (pivot + 1 < right) {
                    starts.add(pivot + 1);
                    ends.add(right);
                    n++;
                }
            }
        }
    }

    private int partition(double[] nums, int start, int end) {
        double pivot = nums[start];

        int left = start - 1;
        int right = end + 1;

        while (true) {

            while (nums[++left] < pivot) {
            }

            while (nums[--right] > pivot) {
            }

            if (left < right) {
                swap(nums, left, right);
            } else {
                break;
            }

        }

        return right;
    }
}
