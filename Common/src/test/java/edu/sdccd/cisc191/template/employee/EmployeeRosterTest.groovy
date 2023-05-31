package edu.sdccd.cisc191.template.employee

import org.junit.jupiter.api.Test

// MODULE 1

public class EmployeeRosterTest {

    @Test
    public void testGetAtIndex() {
        EmployeeRoster roster = new EmployeeRoster();
        Cashier cashier1 = new Cashier("John Doe", 0.0, 0.0);
        Cashier cashier2 = new Cashier("Jane Smith", 0.0, 0.0);

        roster.hireEmployee(cashier1);
        roster.hireEmployee(cashier2);

        Cashier result1 = (Cashier) roster.getAtIndex(0);
        Cashier result2 = (Cashier) roster.getAtIndex(1);
        Employee result3 = roster.getAtIndex(2);

        assertEquals(cashier1, result1);
        assertEquals(cashier2, result2);
        assertNull(result3);
    }

    @Test
    public void testSetAtIndex() {
        EmployeeRoster roster = new EmployeeRoster();
        Cook cook = new Cook("John Doe", 0.0, 0.0);

        roster.hireEmployee(cook);

        Cashier newCashier = new Cashier("Jane Smith", 0.0, 0.0);
        roster.setAtIndex(0, newCashier);

        Employee result = roster.getAtIndex(0);

        assertEquals(newCashier, result);
    }

    @Test
    public void testFindIndexOf() {
        EmployeeRoster roster = new EmployeeRoster();
        Cashier cashier1 = new Cashier("John Doe", 0.0, 0.0);
        Cook cook = new Cook("Jane Smith", 0.0, 0.0);

        roster.hireEmployee(cashier1);
        roster.hireEmployee(cook);

        int index1 = roster.findIndexOf(cashier1);
        int index2 = roster.findIndexOf(cook);
        int index3 = roster.findIndexOf(new Cashier("Unknown", 0.0, 0.0));

        assertEquals(0, index1);
        assertEquals(1, index2);
        assertEquals(-1, index3);
    }

    @Test
    public void testIsEmpty() {
        EmployeeRoster roster = new EmployeeRoster();
        assertTrue(roster.isEmpty());

        Cashier cashier = new Cashier("John Doe", 0.0, 0.0);
        roster.hireEmployee(cashier);

        assertFalse(roster.isEmpty());
    }

    @Test
    public void testHasOneEmployee() {
        EmployeeRoster roster = new EmployeeRoster();
        assertFalse(roster.hasOneEmployee());

        Cashier cashier1 = new Cashier("John Doe", 0.0, 0.0);
        roster.hireEmployee(cashier1);
        assertTrue(roster.hasOneEmployee());

        Cashier cashier2 = new Cashier("Jane Smith", 0.0, 0.0);
        roster.hireEmployee(cashier2);
        assertFalse(roster.hasOneEmployee());
    }

    @Test
    public void testPrintAll() {
        EmployeeRoster roster = new EmployeeRoster();
        Cashier cashier1 = new Cashier("John Doe", 0.0, 0.0);
        Cook cook = new Cook("Jane Smith", 0.0, 0.0);

        roster.hireEmployee(cashier1);
        roster.hireEmployee(cook);

        String expected = cashier1 + "\n\n" + cook + "\n\n";
        String result = roster.printAll();

        assertEquals(expected, result);
    }

    @Test
    public void testDeleteAtIndex() {
        EmployeeRoster roster = new EmployeeRoster();
        Cashier cashier1 = new Cashier("John Doe", 0.0, 0.0);
        Cashier cashier2 = new Cashier("Jane Smith", 0.0, 0.0);

        roster.hireEmployee(cashier1);
        roster.hireEmployee(cashier2);

        roster.deleteAtIndex(0);

        Employee result1 = roster.getAtIndex(0);
        assertNull(result1);

        Employee result2 = roster.getAtIndex(1);
        assertEquals(cashier2, result2);
    }

    @Test
    public void testHireEmployee() {
        EmployeeRoster roster = new EmployeeRoster();
        Cashier cashier = new Cashier("John Doe", 0.0, 0.0);

        roster.hireEmployee(cashier);

        Employee result = roster.getAtIndex(0);

        assertEquals(cashier, result);
    }

    @Test
    public void testFireEmployee() {
        EmployeeRoster roster = new EmployeeRoster();
        Cashier cashier = new Cashier("John Doe", 0.0, 0.0);

        roster.hireEmployee(cashier);

        roster.fireEmployee(cashier);

        Employee result = roster.getAtIndex(0);

        assertNull(result);
        assertEquals(0.0, cashier.getSalary(), 0.0);
    }

    @Test
    public void testSearchByName() {
        EmployeeRoster roster = new EmployeeRoster();
        Cashier cashier1 = new Cashier("John Doe", 0.0, 0.0);
        Cashier cashier2 = new Cashier("Jane Smith", 0.0, 0.0);

        roster.hireEmployee(cashier1);
        roster.hireEmployee(cashier2);

        Employee result1 = roster.searchByName("john doe");
        Employee result2 = roster.searchByName("Jane Smith");
        Employee result3 = roster.searchByName("Unknown");

        assertEquals(cashier1, result1);
        assertEquals(cashier2, result2);
        assertNull(result3);
    }
}
