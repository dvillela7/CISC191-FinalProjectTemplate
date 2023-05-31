package edu.sdccd.cisc191.template.employee;
import java.util.Arrays;

// MODULE 1

public class EmployeeRoster {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;
    private static final double SHRINK_THRESHOLD = 0.25;

    private Employee[] roster;
    private int size;

    public EmployeeRoster() {
        roster = new Employee[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Returns the employee at the specified index in the roster.
     *
     * @param index the index of the employee
     * @return the employee at the specified index, or null if the index is invalid
     */
    public Employee getAtIndex(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return roster[index];
    }

    /**
     * Sets the employee at the specified index in the roster.
     *
     * @param index    the index of the employee
     * @param employee the employee to set at the specified index
     */
    public void setAtIndex(int index, Employee employee) {
        if (index < 0 || index >= size) {
            return;
        }
        roster[index] = employee;
    }

    /**
     * Finds the index of the specified employee in the roster.
     *
     * @param employee the employee to find
     * @return the index of the employee, or -1 if not found
     */
    public int findIndexOf(Employee employee) {
        for (int i = 0; i < size; i++) {
            if (employee != null && employee.equals(roster[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if the roster is empty.
     *
     * @return true if the roster is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the roster has only one employee.
     *
     * @return true if the roster has only one employee, false otherwise
     */
    public boolean hasOneEmployee() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i] != null) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    /**
     * Prints all employees in the roster.
     *
     * @return a string representation of all employees in the roster
     */
    public String printAll() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            Employee employee = roster[i];
            if (employee != null) {
                stringBuilder.append(employee).append("\n\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Deletes the employee at the specified index in the roster.
     *
     * @param index the index of the employee to delete
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        System.arraycopy(roster, index + 1, roster, index, size - index - 1);
        roster[size - 1] = null;
        size--;

        if (shouldShrink()) {
            shrink();
        }
    }

    /**
     * Hires an employee and adds them to the roster.
     *
     * @param employee The employee to be hired.
     */
    public void hireEmployee(Employee employee) {
        if (employee == null) {
            return;
        }

        if (size == roster.length) {
            expand();
        }

        roster[size] = employee;
        size++;
    }

    /**
     * Fires an employee from the roster.
     *
     * @param employee The employee to be fired.
     */
    public void fireEmployee(Employee employee) {
        int index = findIndexOf(employee);
        if (index != -1) {
            deleteAtIndex(index);
            employee.setSalary(0.0);
            setAtIndex(index, employee);
        }
    }

    /**
     * Searches for an employee by their name.
     *
     * @param name The name of the employee to search for.
     * @return The employee with the specified name, or null if not found.
     */
    public Employee searchByName(String name) {
        for (int i = 0; i < size; i++) {
            Employee employee = roster[i];
            if (employee != null && employee.getName().equalsIgnoreCase(name)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Expands the roster capacity by increasing its size.
     * This method is called internally when the roster is full and needs to accommodate more employees.
     */
    private void expand() {
        int newCapacity = roster.length * GROWTH_FACTOR;
        roster = Arrays.copyOf(roster, newCapacity);
    }

    /**
     * Shrinks the roster capacity.
     */
    private void shrink() {
        int newCapacity = Math.max(roster.length / GROWTH_FACTOR, DEFAULT_CAPACITY);
        roster = Arrays.copyOf(roster, newCapacity);
    }

    /**
     * Checks if the roster should be shrunk based on the current number of employees.
     *
     * @return true if the roster should be shrunk, false otherwise.
     */
    private boolean shouldShrink() {
        return size < roster.length * SHRINK_THRESHOLD;
    }
}