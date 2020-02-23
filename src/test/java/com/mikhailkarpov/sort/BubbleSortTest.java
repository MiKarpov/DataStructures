package com.mikhailkarpov.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class BubbleSortTest {

    @Test
    public void testSortIntArray() {
        Integer[] intArray = {-5, 6, 15, 3, -23, 17};
        Integer[] expectedSortedIntArray = {-23, -5, 3, 6, 15, 17};

        BubbleSort.sort(intArray);

        assertArrayEquals(expectedSortedIntArray, intArray);
    }

    @Test
    public void testSortIntArray2() {
        final int NUM = 10000;
        final Random random = new Random();

        Integer[] intArray = new Integer[NUM];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(NUM);
        }

        Integer[] copy = intArray.clone();

        BubbleSort.sort(intArray);
        Arrays.sort(copy);

        assertArrayEquals(copy, intArray);
    }

    @Test
    public void testSortStringArray() {
        String[] array = {"abcd", "ab", "c", "a", "d", "b", "cdba"};
        String[] copy = array.clone();

        BubbleSort.sort(array);
        Arrays.sort(copy);

        assertArrayEquals(copy, array);
    }
}