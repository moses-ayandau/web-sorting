package com.moses.sorting.model;
public class SortingResult {
    private String sortedArray;
    private int swaps;
    private int passes;
    private String timeComplexity;

    public SortingResult(String sortedArray, int swaps, int passes, String timeComplexity) {
        this.sortedArray = sortedArray;
        this.swaps = swaps;
        this.passes = passes;
        this.timeComplexity = timeComplexity;
    }

    public String getSortedArray() {
        return sortedArray;
    }

    public int getSwaps() {
        return swaps;
    }

    public int getPasses() {
        return passes;
    }

    public String getTimeComplexity() {
        return timeComplexity;
    }
}
