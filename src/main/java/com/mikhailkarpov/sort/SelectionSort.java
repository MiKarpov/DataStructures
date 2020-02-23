package com.mikhailkarpov.sort;

import com.mikhailkarpov.util.ArraysUtils;

public class SelectionSort {

    public static <T extends Comparable<T>>  void sort(T[] array) {
        for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            int indexOfLargest = 0;
            for (int i = 0; i <= lastUnsortedIndex; i++) {
                if (array[i].compareTo(array[indexOfLargest]) > 0) {
                    indexOfLargest = i;
                }
            }
            ArraysUtils.swapElements(array, indexOfLargest, lastUnsortedIndex);
        }
    }
}