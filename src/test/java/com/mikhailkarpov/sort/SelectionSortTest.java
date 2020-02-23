package com.mikhailkarpov.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class SelectionSortTest {

    @Test
    public void testSelectionSortIntArray() {
        Integer[] array = {15, -7, 6, -25, 18, 6};
        Integer[] expectedArray = {-25, -7, 6, 6, 15, 18};

        SelectionSort.sort(array);

        assertArrayEquals(expectedArray, array);
    }

    @Test
    public void testSelectionSort_withRandoms() {
        int NUMS = 10000;
        Random random = new Random();

        Integer[] array = new Integer[NUMS];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(NUMS);
        }

        Integer[] copy = array.clone();

        SelectionSort.sort(array);
        Arrays.sort(copy);

        assertArrayEquals(copy, array);
    }

}