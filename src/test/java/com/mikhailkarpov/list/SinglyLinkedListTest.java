package com.mikhailkarpov.list;

import com.mikhailkarpov.dto.Employee;
import org.junit.After;
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

    @After
    public void logEmployee() {
        System.out.println("Employees:" + employees);
        System.out.println("Size: " + employees.size());
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
        assertTrue(employees.contains(testEmployee));
        assertEquals(0, employees.indexOf(testEmployee));

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
        assertTrue(employees.contains(testEmployee));
        assertEquals(5, employees.indexOf(testEmployee));

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
        assertFalse(employees.contains(testEmployee));
        assertEquals(-1, employees.indexOf(testEmployee));

        Iterator<Employee> iterator = employees.iterator();

        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void multipleRemoveFirstShouldAdjustSizeAndIterator() {
        populateEmployees();

        assertFalse(employees.isEmpty());
        assertEquals(6, employees.size());

        for (int i = 6; i > 0; i--) {
            assertEquals(i, employees.size());
            Iterator<Employee> iterator = employees.iterator();
            assertTrue(iterator.hasNext());

            Employee nextEmployee = iterator.next();
            Employee removedEmployee = employees.removeFirst();
            assertFalse(employees.contains(removedEmployee));
            assertEquals(-1, employees.indexOf(removedEmployee));

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

    @Test (expected = RuntimeException.class)
    public void removeAtInvalidIndexShouldThrowException() {
        populateEmployees();
        employees.remove(6);
    }

    @Test
    public void removeShouldAdjustList() {
        populateEmployees();
        Employee removedEmployee = employees.remove(5);

        assertFalse(employees.contains(removedEmployee));
        assertEquals(1, removedEmployee.getId());
        assertEquals(5, employees.size());

        System.out.println(employees);

        removedEmployee = employees.remove(2);
        assertFalse(employees.contains(removedEmployee));
        assertEquals(4, removedEmployee.getId());
        assertEquals(4, employees.size());
    }

    @Test
    public void testClear() {
        populateEmployees();

        employees.clear();

        assertTrue(employees.isEmpty());
        assertEquals(0, employees.size());
        assertFalse(employees.contains(testEmployee));
        assertEquals(-1, employees.indexOf(testEmployee));

        Iterator<Employee> iterator = employees.iterator();
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void testAddAt() {
        populateEmployees();
        Employee addedEmployee = new Employee(7, "addedEmployee");
        employees.addAt(0, addedEmployee);

        assertEquals(7, employees.size());
        assertTrue(employees.contains(addedEmployee));
        assertEquals(0, employees.indexOf(addedEmployee));
        assertEquals(6, employees.indexOf(testEmployee));

        addedEmployee = new Employee(8, "oneMoreAddedEmployee");
        employees.addAt(7, addedEmployee);

        assertEquals(8, employees.size());
        assertTrue(employees.contains(addedEmployee));
        assertEquals(7, employees.indexOf(addedEmployee));

        addedEmployee = new Employee(9, "anotherAddedEmployee");
        employees.addAt(3, addedEmployee);

        assertEquals(9, employees.size());
        assertTrue(employees.contains(addedEmployee));
        assertEquals(3, employees.indexOf(addedEmployee));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtNegativeIndexShouldThrowException() {
        employees.addAt(-1, testEmployee);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtInvalidIndexShouldThrowException() {
        populateEmployees();
        employees.addAt(7, testEmployee);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getInvalidIndexShouldThrowException() {
        populateEmployees();
        employees.get(6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIfNegativeIndexThrowException() {
        populateEmployees();
        employees.get(-1);
    }

    @Test
    public void testGet() {
        employees.addFirst(new Employee(2, "2"));
        employees.addFirst(new Employee(1, "1"));
        employees.addFirst(new Employee(0, "0"));
        for (int i = 0; i < employees.size(); i++) {
            assertEquals(i, employees.get(i).getId());
        }
    }

    @Test
    public void getFirstShouldReturnFirstElement() {
        populateEmployees();
        Employee first = employees.getFirst();

        assertNotNull(first);
        assertEquals(6, first.getId());
    }

    @Test
    public void setInEmptyListShouldAddElement() {
        assertTrue(employees.set(0, testEmployee));
        assertFalse(employees.isEmpty());
        assertTrue(employees.contains(testEmployee));
        assertEquals(1, employees.size());
        assertEquals(testEmployee, employees.get(0));
    }

    @Test
    public void setAtIndexShouldEditList() {
        populateEmployees();
        Employee employee = new Employee(7, "7");

        assertTrue(employees.set(3, employee));
        assertTrue(employees.contains(employee));
        assertEquals(6, employees.size());
        assertEquals(employee, employees.get(3));

        System.out.println("Employees: " + employees);

        Employee anotherEmployee = new Employee(8, "8");

        assertTrue(employees.set(3, anotherEmployee));
        assertTrue(employees.contains(anotherEmployee));
        assertEquals(6, employees.size());
        assertEquals(anotherEmployee, employees.get(3));

        assertFalse(employees.contains(employee));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setIllegalIndexAtEmptyListShouldThrowException() {
        employees.set(1, testEmployee);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setIllegalIndexShouldThrowException() {
        populateEmployees();
        employees.set(7, testEmployee);
    }
}