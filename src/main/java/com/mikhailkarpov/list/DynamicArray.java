package com.mikhailkarpov.list;

public class DynamicArray<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private T[] arr;
    private int size;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        arr = (T[]) new Object[capacity];
    }

    public void add(T elem) {
        int length = arr.length;
        if (length == size) {
            T[] new_arr = (T[]) new Object[length * 2];
            System.arraycopy(arr, 0, new_arr, 0, length);
            arr = new_arr;
        }
        arr[size] = elem;
        size++;
    }

    public void clear() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public boolean contains(T elem) {
        return indexOf(elem) != -1;
    }

    public T get(int index) {
        checkIndexValid(index);
        return arr[index];
    }

    public int indexOf(T elem) {
        for (int i = 0; i < arr.length; i++) {
            if (elem.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean remove(T elem) {
        int index = indexOf(elem);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    public void remove(int index) {
        checkIndexValid(index);

        T[] new_arr = (T[]) new Object[arr.length - 1];

        for (int i =0, j = 0; i < arr.length; i++, j++) {
            if (i == index) {
                j--;
            } else {
                new_arr[j] = arr[i];
            }
        }
        arr = new_arr;
        size--;
    }

    public void set(int index, T elem) {
        checkIndexValid(index);
        arr[index] = elem;
    }

    public int size() {
        return size;
    }

    private void checkIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
