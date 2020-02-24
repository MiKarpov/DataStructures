package com.mikhailkarpov.sort;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;

public class SortTest {

    private Integer[] integers;
    private Integer[] sortedIntegers;
    private String[] strings;
    private String[] sortedStrings;

    @Before
    public void setIntegers() {
        integers = new Integer[]{15, -6, 1, 22, 7, -13, 6, 7};
        sortedIntegers = new Integer[]{-13, -6, 1, 6, 7, 7, 15, 22};
    }

    @Before
    public void setStrings() {
        strings = new String[]{"abc", "a", "bca", "c", "cba", "b", "c", "bac"};
        sortedStrings = new String[]{"a", "abc", "b", "bac", "bca", "c", "c", "cba"};
    }

    @Test
    public void testBubbleSort_IntArray() {
        BubbleSort.sort(integers);
        assertArrayEquals(sortedIntegers, integers);
    }

    @Test
    public void testBubbleSort_StringArray() {
        BubbleSort.sort(strings);
        assertArrayEquals(sortedStrings, strings);
    }

    @Test
    public void testSelectionSort_IntArray() {
        SelectionSort.sort(integers);
        assertArrayEquals(sortedIntegers, integers);
    }

    @Test
    public void testSelectionSort_StringArray() {
        SelectionSort.sort(strings);
        assertArrayEquals(sortedStrings, strings);
    }

    @Test
    public void testQuickSort_IntArray() {
        QuickSort.sort(integers);
        assertArrayEquals(sortedIntegers, integers);
    }

    @Test
    public void testQuickSort_StringArray() {
        QuickSort.sort(strings);
        assertArrayEquals(sortedStrings, strings);
    }
}
