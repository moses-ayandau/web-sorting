package com.moses.sorting.service.impl;

import com.moses.sorting.model.SortingResult;
import com.moses.sorting.service.SortingStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component("bucket")
public class BucketSortStrategy implements SortingStrategy {
    @Override
    public SortingResult sort(int[] array) {
        List<Float> floatArray = new ArrayList<>();
        for (int num : array) {
            floatArray.add((float) num / 100);
        }

        Float[] sortedFloatArray = new Float[floatArray.size()];
        floatArray.toArray(sortedFloatArray);
        bucketSortFloats(sortedFloatArray);

        return new SortingResult(Arrays.toString(sortedFloatArray), 0, 0, "O(n)");

    }
    public void bucketSortFloats(Float[] array) {
        int n = array.length;
        if (n <= 1) return;

        float min = Collections.min(Arrays.asList(array));
        float max = Collections.max(Arrays.asList(array));

        int bucketCount = n;
        List<Float>[] buckets = new List[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (Float f : array) {
            int bucketIndex = (int) ((f - min) / (max - min) * (bucketCount - 1));
            buckets[bucketIndex].add(f);
        }

        int index = 0;
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
            for (Float value : bucket) {
                array[index++] = value;
            }
        }
    }
}
