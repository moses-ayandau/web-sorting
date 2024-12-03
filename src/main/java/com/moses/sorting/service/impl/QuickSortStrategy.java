package com.moses.sorting.service.impl;

import com.moses.sorting.model.SortingResult;
import com.moses.sorting.service.SortingStrategy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("quick")
public class QuickSortStrategy implements SortingStrategy {

    private int swaps = 0;
    private int passes = 0;

    @Override
    public SortingResult sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return new SortingResult(Arrays.toString(array), swaps, passes, "O(n log n) average");
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            swaps++;
            passes++;

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swaps++;
    }
}
