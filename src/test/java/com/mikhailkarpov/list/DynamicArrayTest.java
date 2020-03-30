package com.mikhailkarpov.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DynamicArrayTest {

    DynamicArray<String> dynamicArray;
    String FIRST_TEST_STRING = "string";
    String SECOND_TEST_STRING = "another string";
    String THIRD_TEST_STRING = "one more string";

    @Before
    public void setUp() throws Exception {
        dynamicArray = new DynamicArray<>();

        dynamicArray.add(FIRST_TEST_STRING);
        dynamicArray.add(SECOND_TEST_STRING);
    }

    @Test
    public void defaultConstructor_shouldCreateEmptyArray() {
        dynamicArray = new DynamicArray<>();

        assertTrue(dynamicArray.isEmpty());
        assertEquals(0, dynamicArray.size());
    }

    @Test
    public void constructorWithCapacity_shouldCreateEmptyArray() {
        dynamicArray = new DynamicArray<>(32);

        assertTrue(dynamicArray.isEmpty());
        assertEquals(0, dynamicArray.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorWithIllegalCapacity_shouldThrowIllegalArgumentException() {
        dynamicArray = new DynamicArray<>(-1);
    }

    @Test
    public void addElements_shouldAddElement() {
        dynamicArray.add(THIRD_TEST_STRING);

        assertTrue(dynamicArray.contains(THIRD_TEST_STRING));
        assertEquals(THIRD_TEST_STRING, dynamicArray.get(2));
    }

    @Test
    public void addElement_shouldIncreaseSize() {
        dynamicArray.add(THIRD_TEST_STRING);

        assertFalse(dynamicArray.isEmpty());
        assertEquals(3, dynamicArray.size());
    }

    @Test
    public void getByIndex_shouldReturnElement() {
        assertEquals(FIRST_TEST_STRING, dynamicArray.get(0));
        assertEquals(SECOND_TEST_STRING, dynamicArray.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getByIllegalIndex_shouldThrowIndexOutOfBoundsException() {
        dynamicArray.get(2);
    }

    @Test
    public void set_byIndex_shouldSetNewElement() {
        dynamicArray.set(0, SECOND_TEST_STRING);
        dynamicArray.set(1, THIRD_TEST_STRING);

        assertEquals(SECOND_TEST_STRING, dynamicArray.get(0));
        assertEquals(THIRD_TEST_STRING, dynamicArray.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void set_ByIllegalIndex_shouldThrowIndexOutOfBoundsException() {
        dynamicArray.set(2, THIRD_TEST_STRING);
    }

    @Test
    public void indexOfElement_shouldReturnIndexOrMinus1() {
        assertEquals(0, dynamicArray.indexOf(FIRST_TEST_STRING));
        assertEquals(1, dynamicArray.indexOf(SECOND_TEST_STRING));
        assertEquals(-1, dynamicArray.indexOf(THIRD_TEST_STRING));
    }

    @Test
    public void removeByIndex_shouldRemoveElementAndDecreaseSizeAndRearrangeElements() {
        dynamicArray.remove(0);

        assertFalse(dynamicArray.contains(FIRST_TEST_STRING));
        assertEquals(1, dynamicArray.size());
        assertEquals(SECOND_TEST_STRING, dynamicArray.get(0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeByIllegalIndex_shouldThrowIndexOutOfBoundsException() {
        dynamicArray.remove(2);
    }

    @Test
    public void removeByElement_shouldRemoveElementAndDecreaseSizeAndRearrangeElements_andReturnTrue() {
        boolean removed = dynamicArray.remove(FIRST_TEST_STRING);

        assertTrue(removed);
        assertFalse(dynamicArray.contains(FIRST_TEST_STRING));
        assertEquals(1, dynamicArray.size());
        assertEquals(SECOND_TEST_STRING, dynamicArray.get(0));
    }

    @Test
    public void removeIllegalElement_shouldReturnFalse() {
        assertFalse(dynamicArray.remove(THIRD_TEST_STRING));
    }

    @Test
    public void clear_shouldRemoveElementsAndSetZeroSize() {
        dynamicArray.clear();

        assertFalse(dynamicArray.contains(FIRST_TEST_STRING));
        assertFalse(dynamicArray.contains(SECOND_TEST_STRING));
        assertTrue(dynamicArray.isEmpty());
        assertEquals(0, dynamicArray.size());
    }
}