package com.mikhailkarpov.list;

import com.mikhailkarpov.dto.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {

    private DoublyLinkedList<Employee> employees;
    private final Employee testEmployee1 = new Employee(1, "1");
    private final Employee testEmployee2 = new Employee(2, "2");
    private final Employee testEmployee3 = new Employee(3, "3");

    @Before
    public void setUp() {
        employees = new DoublyLinkedList<>();
    }

    @After
    public void printList() {
        System.out.println(employees);
        System.out.println("Size: " + employees.size());
    }

    @Test
    public void afterConstructionShouldBeEmpty() {
        assertTrue(employees.isEmpty());
        assertEquals(0, employees.size());
    }

    @Test
    public void test_addFirst() {
        employees.addFirst(testEmployee1);

        assertFalse(employees.isEmpty());
        assertTrue(employees.contains(testEmployee1));
        assertEquals(1, employees.size());
        assertEquals(testEmployee1, employees.get(0));
        assertEquals(testEmployee1, employees.getFirst());
        assertEquals(testEmployee1, employees.getLast());
    }

    @Test
    public void testMultipleAddFirst() {
        employees.addFirst(testEmployee1);
        employees.addFirst(testEmployee2);
        employees.addFirst(testEmployee3);

        assertFalse(employees.isEmpty());
        assertTrue(employees.contains(testEmployee1));
        assertTrue(employees.contains(testEmployee2));
        assertTrue(employees.contains(testEmployee3));
        assertEquals(3, employees.size());
        assertEquals(testEmployee3, employees.getFirst());
        assertEquals(testEmployee2, employees.get(1));
        assertEquals(testEmployee1, employees.getLast());
    }

    @Test
    public void test_addLast() {
        employees.addLast(testEmployee1);

        assertFalse(employees.isEmpty());
        assertTrue(employees.contains(testEmployee1));
        assertEquals(1, employees.size());
        assertEquals(testEmployee1, employees.get(0));
        assertEquals(testEmployee1, employees.getFirst());
        assertEquals(testEmployee1, employees.getLast());
    }

    @Test
    public void testMultipleAddLast() {
        employees.addLast(testEmployee1);
        employees.addLast(testEmployee2);
        employees.addLast(testEmployee3);

        assertFalse(employees.isEmpty());
        assertTrue(employees.contains(testEmployee1));
        assertTrue(employees.contains(testEmployee2));
        assertTrue(employees.contains(testEmployee3));
        assertEquals(3, employees.size());
        assertEquals(testEmployee1, employees.getFirst());
        assertEquals(testEmployee2, employees.get(1));
        assertEquals(testEmployee3, employees.getLast());
    }

    @Test
    public void testAddAt() {
        employees.add(0, testEmployee1);
        employees.add(1, testEmployee2);
        employees.add(1, testEmployee3);

        assertFalse(employees.isEmpty());
        assertTrue(employees.contains(testEmployee1));
        assertTrue(employees.contains(testEmployee2));
        assertTrue(employees.contains(testEmployee3));
        assertEquals(3, employees.size());
        assertEquals(testEmployee1, employees.getFirst());
        assertEquals(testEmployee3, employees.get(1));
        assertEquals(testEmployee2, employees.getLast());
    }

    @Test
    public void testClear() {
        employees.addFirst(testEmployee1);
        employees.addLast(testEmployee2);
        employees.add(0, testEmployee3);
        employees.clear();

        assertTrue(employees.isEmpty());
        assertEquals(0, employees.size());
        assertFalse(employees.contains(testEmployee1));
        assertFalse(employees.contains(testEmployee2));
        assertFalse(employees.contains(testEmployee3));
    }

    @Test
    public void testGet() {
        employees.add(0, testEmployee1);
        employees.add(1, testEmployee2);
        employees.add(1, testEmployee3);

        assertEquals(testEmployee1, employees.get(0));
        assertEquals(testEmployee1, employees.getFirst());
        assertEquals(testEmployee2, employees.get(2));
        assertEquals(testEmployee2, employees.getLast());
        assertEquals(testEmployee3, employees.get(1));
    }

    @Test
    public void testIndexOf() {
        employees.add(0, testEmployee1);
        employees.add(1, testEmployee2);
        employees.add(1, testEmployee3);

        assertEquals(0, employees.indexOf(testEmployee1));
        assertEquals(1, employees.indexOf(testEmployee3));
        assertEquals(2, employees.indexOf(testEmployee2));
    }

    @Test
    public void testRemove() {
        employees.add(0, testEmployee1);
        employees.add(1, testEmployee2);
        employees.add(1, testEmployee3);

        System.out.println(employees);

        assertEquals(testEmployee3, employees.remove(1));
        assertEquals(2, employees.size());
        assertFalse(employees.contains(testEmployee3));
        assertEquals(testEmployee1, employees.getFirst());
        assertEquals(testEmployee2, employees.getLast());

        System.out.println(employees);

        assertEquals(testEmployee2, employees.remove(1));
        assertEquals(1, employees.size());
        assertFalse(employees.contains(testEmployee2));
        assertEquals(testEmployee1, employees.getFirst());
        assertEquals(testEmployee1, employees.getLast());

        System.out.println(employees);

        assertEquals(testEmployee1, employees.remove(0));
        assertTrue(employees.isEmpty());
        assertEquals(0, employees.size());
        assertFalse(employees.contains(testEmployee1));
    }

    @Test
    public void testSet() {
        employees.addFirst(testEmployee1);
        employees.add(1, testEmployee2);
        employees.addLast(testEmployee3);

        employees.set(0, testEmployee3);
        employees.set(1, testEmployee1);
        employees.set(2, testEmployee2);

        assertEquals(3, employees.size());
        assertTrue(employees.contains(testEmployee1));
        assertTrue(employees.contains(testEmployee2));
        assertTrue(employees.contains(testEmployee3));

        assertEquals(testEmployee3, employees.get(0));
        assertEquals(testEmployee1, employees.get(1));
        assertEquals(testEmployee2, employees.get(2));
    }

    @Test
    public void testIterator() {
        employees.addFirst(testEmployee1);
        employees.add(1, testEmployee2);
        employees.addLast(testEmployee3);

        Iterator<Employee> iterator = employees.iterator();
        for (int i = 0; i < 3; i++) {
            assertTrue(iterator.hasNext());
            Employee next = iterator.next();
            assertNotNull(next);
            assertEquals(i + 1, next.getId());
        }

        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }
}