package com.mikhailkarpov.sort;

public class BubbleSort {

    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            for (int i = 0; i < lastUnsortedIndex; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                }
            }
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        if (i == j) {
            return;
        }

        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
