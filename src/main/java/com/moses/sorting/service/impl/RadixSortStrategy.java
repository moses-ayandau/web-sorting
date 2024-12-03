package com.moses.sorting.service.impl;

import com.moses.sorting.model.SortingResult;
import com.moses.sorting.service.SortingStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Component("radix")
public class RadixSortStrategy implements SortingStrategy {
    private int passes = 0;
    @Override
    public SortingResult sort(int[] array) {

        int max = getMax(array);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
            passes++;
        }

        int swaps = 0;
        return new SortingResult(Arrays.toString(array), swaps, passes, "O(k * n)");

    }
    private int getMax(int[] array) {
        return Arrays.stream(array).max().orElse(Integer.MIN_VALUE);
    }
    private void countingSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int value : array) {
            count[(value / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }
}
