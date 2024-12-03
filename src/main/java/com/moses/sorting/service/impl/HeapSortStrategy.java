package com.moses.sorting.service.impl;

import com.moses.sorting.model.SortingResult;
import com.moses.sorting.service.SortingStrategy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("heap")
public class HeapSortStrategy implements SortingStrategy {
    @Override
    public SortingResult sort(int[] array) {
        int n = array.length;
        int swaps = 0;
        int passes = 0;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
            passes++;
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            swaps++;

            heapify(array, i, 0);
            passes++;
        }

        return new SortingResult(Arrays.toString(array), swaps, passes, "O(n log n)");

    }
    private void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapify(array, n, largest);
        }
    }
}
