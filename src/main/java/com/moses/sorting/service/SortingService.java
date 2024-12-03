package com.moses.sorting.service;

import com.moses.sorting.model.SortingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class SortingService {

    private final Map<String, SortingStrategy> sortingStrategies;

    @Autowired
    public SortingService(Map<String, SortingStrategy> sortingStrategies) {
        this.sortingStrategies = sortingStrategies;
    }

    public SortingResult sortArray(String algorithm, String arrayInput) {
        int[] array = parseInputArray(arrayInput);

        System.out.println(algorithm);
        SortingStrategy sortingStrategy = sortingStrategies.get(algorithm.toLowerCase());
        System.out.println(sortingStrategy);
        if (sortingStrategy == null) {
            throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }


        return sortingStrategy.sort(array);
    }

    private int[] parseInputArray(String arrayInput) {
        try {
            return Arrays.stream(arrayInput.split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input array. Ensure the array contains only integers separated by commas.");
        }
    }
}
