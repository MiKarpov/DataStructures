package com.mikhailkarpov.sort;

import com.mikhailkarpov.util.ArraysUtils;

public class QuickSort {

    public static <T extends Comparable<T>> void sort(T[] input) {
        if (input == null)
            throw new IllegalArgumentException("Array is null");
        if (input.length == 0)
            throw new IllegalArgumentException("Array's length is 0");

        int start = 0;
        int end = input.length - 1;
        quickSort(input, start, end);
    }

    private static <T extends Comparable<T>> void quickSort(T[] input, int start, int end) {
        if (input == null || input.length == 0 || start >= end) {
            return;
        }

        // pick the pivot
        int middle = start + (end - start) / 2;
        T pivot = input[middle];

        // make left < pivot and right > pivot
        int i = start;
        int j = end;
        while (i <= j) {
            while (input[i].compareTo(pivot) < 0) {
                i++;
            }
            while (input[j].compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                ArraysUtils.swapElements(input, i, j);
                i++;
                j--;
            }

            //recursively sort tow sub arrays
            if (start < j) {
                quickSort(input, start, j);
            }
            if (end > i) {
                quickSort(input, i, end);
            }
        }
    }
}