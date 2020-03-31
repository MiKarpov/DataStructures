package com.mikhailkarpov.list;

import com.mikhailkarpov.dto.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SinglyLinkedListTest {

    private SinglyLinkedList<Employee> employees;
    private Employee testEmployee = new Employee(1, "Mike");

    @Before
    public void setUp() {
        employees = new SinglyLinkedList<>();
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
    public void afterMultipleAddFirstShouldStoreElementsAndIncreaseSize() {
        populateEmployees();

        assertFalse(employees.isEmpty());
        assertEquals(6, employees.size());

        Iterator<Employee> iterator = employees.iterator();

        assertNotNull(iterator);
        for (int i = 0; i < 6; i++) {
            assertTrue(iterator.hasNext());
            assertNotNull(iterator.next());
        }
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    private void populateEmployees() {
        employees.addFirst(testEmployee);
        employees.addFirst(new Employee(2, "Andrew"));
        employees.addFirst(new Employee(3, "Andrew"));
        employees.addFirst(new Employee(4, "Andrew"));
        employees.addFirst(new Employee(5, "Andrew"));
        employees.addFirst(new Employee(6, "Andrew"));
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
    public void multipleRemoveFromFrontShouldAdjustSizeAndIterator() {
        populateEmployees();

        assertFalse(employees.isEmpty());
        assertEquals(6, employees.size());

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
}