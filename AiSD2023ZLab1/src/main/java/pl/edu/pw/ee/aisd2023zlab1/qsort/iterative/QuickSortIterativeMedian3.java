package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import static pl.edu.pw.ee.aisd2023zlab1.services.SortingUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSortIterativeMedian3 implements Sorting {

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
        swap(nums, start, mediana(nums, start, end));

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

    private int mediana(double[] data, int start, int end) {
        Random rand = new Random();
        int randId = rand.nextInt(end - start + 1) + start;

        if ((data[start] >= data[randId] && data[randId] >= data[end]) || (data[end] >= data[randId] && data[randId] >= data[start]))
            return randId;
        else if ((data[randId] >= data[start] && data[start] >= data[end]) || (data[end] >= data[start] && data[start] >= data[randId]))
            return start;
        else
            return end;
    }
}
