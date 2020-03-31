package com.mikhailkarpov.list;

import com.mikhailkarpov.dto.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {

    private DoublyLinkedList<Employee> employees;
    private Employee testEmployee = new Employee(1, "Mike");

    @Before
    public void setUp() {
        employees = new DoublyLinkedList<>();
    }

    @Test
    public void afterConstructionShouldBeEmpty() {
        assertTrue(employees.isEmpty());
        assertEquals(0, employees.size());

        Iterator<Employee> iterator = employees.iterator();

        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void addFirstShouldStoreElementAndIncreaseSize() {
        employees.addFirst(testEmployee);

        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());

        Iterator<Employee> iterator = employees.iterator();

        assertNotNull(iterator);
        assertTrue(iterator.hasNext());
        assertEquals(testEmployee, iterator.next());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void addLastShouldStoreElementAndIncreaseSize() {
        employees.addLast(testEmployee);

        assertFalse(employees.isEmpty());
        assertEquals(1, employees.size());

        Iterator<Employee> iterator = employees.iterator();

        assertNotNull(iterator);
        assertTrue(iterator.hasNext());
        assertEquals(testEmployee, iterator.next());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void afterMultipleAddFirstShouldStoreElementsAndIncreaseSize() {
        populateEmployeesFromFront();

        assertFalse(employees.isEmpty());
        assertEquals(6, employees.size());

        Iterator<Employee> iterator = employees.iterator();
        System.out.println(employees);

        assertNotNull(iterator);
        for (int i = 6; i > 0; i--) {
            assertTrue(iterator.hasNext());

            Employee next = iterator.next();
            assertNotNull(next);
            assertEquals(i, next.getId());
        }
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    private void populateEmployeesFromFront() {
        employees.addFirst(testEmployee);
        employees.addFirst(new Employee(2, "Andrew"));
        employees.addFirst(new Employee(3, "Andrew"));
        employees.addFirst(new Employee(4, "Andrew"));
        employees.addFirst(new Employee(5, "Andrew"));
        employees.addFirst(new Employee(6, "Andrew"));
    }

    @Test
    public void afterMultipleAddLastShouldStoreElementsAndIncreaseSize() {
        populateEmployeesFromBack();

        assertFalse(employees.isEmpty());
        assertEquals(6, employees.size());

        Iterator<Employee> iterator = employees.iterator();
        System.out.println(employees);

        assertNotNull(iterator);
        for (int i = 1; i <= 6; i++) {
            assertTrue(iterator.hasNext());
            Employee next = iterator.next();
            assertNotNull(next);
            assertEquals(i, next.getId());
        }
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    private void populateEmployeesFromBack() {
        employees.addLast(testEmployee);
        employees.addLast(new Employee(2, "Andrew"));
        employees.addLast(new Employee(3, "Andrew"));
        employees.addLast(new Employee(4, "Andrew"));
        employees.addLast(new Employee(5, "Andrew"));
        employees.addLast(new Employee(6, "Andrew"));
    }

    @Test
    public void removeFirstShouldReturnElementAndDecreaseSize() {
        employees.addFirst(testEmployee);

        assertEquals(testEmployee, employees.removeFirst());
        assertTrue(employees.isEmpty());
        assertEquals(0, employees.size());

        Iterator<Employee> iterator = employees.iterator();

        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void removeLastShouldReturnElementAndDecreaseSize() {
        employees.addFirst(testEmployee);

        assertEquals(testEmployee, employees.removeLast());
        assertTrue(employees.isEmpty());
        assertEquals(0, employees.size());

        Iterator<Employee> iterator = employees.iterator();

        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void multipleRemoveFirstShouldAdjustSizeAndIterator() {
        populateEmployeesFromFront();

        assertFalse(employees.isEmpty());
        assertEquals(6, employees.size());

        System.out.println(employees);

        for (int i = 6; i > 0; i--) {
            assertEquals(i, employees.size());
            Iterator<Employee> iterator = employees.iterator();
            assertTrue(iterator.hasNext());

            Employee nextEmployee = iterator.next();
            Employee removedEmployee = employees.removeFirst();

            assertNotNull(removedEmployee);
            assertEquals(nextEmployee, removedEmployee);
            assertEquals(i, removedEmployee.getId());
        }

        assertTrue(employees.isEmpty());
        assertEquals(0, employees.size());

        Iterator<Employee> iterator = employees.iterator();

        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void multipleRemoveLastShouldAdjustSizeAndIterator() {
        populateEmployeesFromBack();

        assertFalse(employees.isEmpty());
        assertEquals(6, employees.size());

        for (int i = 6; i > 0; i--) {
            assertEquals(i, employees.size());
            Iterator<Employee> iterator = employees.iterator();
            assertTrue(iterator.hasNext());

            Employee removedEmployee = employees.removeLast();

            assertNotNull(removedEmployee);
            assertEquals(i, removedEmployee.getId());
        }

        assertTrue(employees.isEmpty());
        assertEquals(0, employees.size());

        Iterator<Employee> iterator = employees.iterator();

        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }
}