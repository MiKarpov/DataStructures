package com.mikhailkarpov.sort;

import com.mikhailkarpov.util.ArraysUtils;

public class BubbleSort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            for (int i = 0; i < lastUnsortedIndex; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    ArraysUtils.swapElements(array, i, i + 1);
                }
            }
        }
    }

}
