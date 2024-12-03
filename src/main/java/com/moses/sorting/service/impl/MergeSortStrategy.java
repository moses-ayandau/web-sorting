package com.moses.sorting.service.impl;

import com.moses.sorting.model.SortingResult;
import com.moses.sorting.service.SortingStrategy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("merge")
public class MergeSortStrategy implements SortingStrategy {

    private int swaps = 0;
    private int passes = 0;

    @Override
    public SortingResult sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
        return new SortingResult(Arrays.toString(array), swaps, passes, "O(n log n)");
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
            passes++;
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temporary arrays
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
                swaps++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
