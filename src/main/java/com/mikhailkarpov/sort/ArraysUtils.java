package com.mikhailkarpov.sort;

public class ArraysUtils {

    public static <T extends Comparable<T>> void swapElements(T[] array, int indexA, int indexB) {
        if (indexA == indexB) {
            return;
        }

        T tmp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = tmp;
    }
}