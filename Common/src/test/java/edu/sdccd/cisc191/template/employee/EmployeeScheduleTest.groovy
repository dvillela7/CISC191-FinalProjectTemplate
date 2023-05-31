package edu.sdccd.cisc191.template.employee

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeScheduleTest {
    private EmployeeSchedule schedule;

    @BeforeEach
    void setUp() {
        schedule = new EmployeeSchedule(2, 2);
        Employee employee1 = new Cashier("John Doe", 0.0, 0.0);
        Employee employee2 = new Cook("Jane Smith", 0.0, 0.0);
        schedule.setAtIndex(0, 0, employee1);
        schedule.setAtIndex(1, 1, employee2);
    }

    @Test
    void testGetAtIndex() {
        Employee employee1 = schedule.getAtIndex(0, 0);
        Employee employee2 = schedule.getAtIndex(1, 1);
        assertNotNull(employee1);
        assertNotNull(employee2);
        assertEquals("John Doe", employee1.getName());
        assertEquals("Jane Smith", employee2.getName());
    }

    @Test
    void testSetAtIndex() {
        Employee employee = new Cook("Alice Johnson", 0.0, 0.0);
        schedule.setAtIndex(1, 0, employee);
        Employee retrievedEmployee = schedule.getAtIndex(1, 0);
        assertNotNull(retrievedEmployee);
        assertEquals("Alice Johnson", retrievedEmployee.getName());
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Cashier("Bob Anderson", 0.0, 0.0);
        schedule.addEmployee(employee);
        Employee retrievedEmployee = schedule.getAtIndex(0, 1);
        assertNotNull(retrievedEmployee);
        assertEquals("Bob Anderson", retrievedEmployee.getName());
    }

    @Test
    void testRemoveEmployee() {
        Employee employee = schedule.getAtIndex(1, 1);
        schedule.removeEmployee(employee);
        Employee retrievedEmployee = schedule.getAtIndex(1, 1);
        assertNull(retrievedEmployee);
    }

    @Test
    void testModifyEmployee() {
        Employee oldEmployee = schedule.getAtIndex(0, 0);
        Employee newEmployee = new Cashier("James Wilson", 0.0, 0.0);
        schedule.modifyEmployee(oldEmployee, newEmployee);
        Employee retrievedEmployee = schedule.getAtIndex(0, 0);
        assertNotNull(retrievedEmployee);
        assertEquals("James Wilson", retrievedEmployee.getName());
    }

    @Test
    void testPrintAll() {
        String expectedOutput = "Row: 0, Column: 0 - John Doe\nRow: 1, Column: 1 - Jane Smith\n";
        String actualOutput = schedule.printAll();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testIsEmpty() {
        assertFalse(schedule.isEmpty());
        schedule.removeEmployee(schedule.getAtIndex(0, 0));
        schedule.removeEmployee(schedule.getAtIndex(1, 1));
        assertTrue(schedule.isEmpty());
    }
}
