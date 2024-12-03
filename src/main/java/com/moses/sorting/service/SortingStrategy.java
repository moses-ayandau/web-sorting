package com.moses.sorting.service;

import com.moses.sorting.model.SortingResult;

public interface SortingStrategy {
    SortingResult sort(int[] array);
}
