package edu.sdccd.cisc191.template.employee;

// MODULE 2

public class EmployeeSchedule {
    private static final int DEFAULT_ROW_SIZE = 10;
    private static final int DEFAULT_COLUMN_SIZE = 10;
    private static final int GROWTH_FACTOR = 2;
    private static final double SHRINK_THRESHOLD = 0.25;

    private Employee[][] schedule;
    private int rowCount;
    private int columnCount;

    public EmployeeSchedule() {
        this(DEFAULT_ROW_SIZE, DEFAULT_COLUMN_SIZE);
    }

    public EmployeeSchedule(int rows, int columns) {
        schedule = new Employee[rows][columns];
        rowCount = rows;
        columnCount = columns;
    }

    /**
     * Returns the employee at the specified index in the schedule.
     *
     * @param row    the row index of the employee
     * @param column the column index of the employee
     * @return the employee at the specified index, or null if the index is invalid
     */
    public Employee getAtIndex(int row, int column) {
        if (isValidIndex(row, column)) {
            return schedule[row][column];
        }
        return null;
    }

    /**
     * Sets the employee at the specified index in the schedule.
     *
     * @param row      the row index of the employee
     * @param column   the column index of the employee
     * @param employee the employee to set at the specified index
     */
    public void setAtIndex(int row, int column, Employee employee) {
        if (isValidIndex(row, column)) {
            schedule[row][column] = employee;
        }
    }

    /**
     * Adds an employee to the schedule.
     *
     * @param employee the employee to add
     */
    public void addEmployee(Employee employee) {
        int[] emptySlot = findIndexOf(null);
        if (emptySlot != null) {
            setAtIndex(emptySlot[0], emptySlot[1], employee);
        } else {
            expand(rowCount * GROWTH_FACTOR, columnCount);
            emptySlot = findIndexOf(null);
            if (emptySlot != null) {
                setAtIndex(emptySlot[0], emptySlot[1], employee);
            }
        }
    }

    /**
     * Removes an employee from the schedule.
     *
     * @param employee the employee to remove
     */
    public void removeEmployee(Employee employee) {
        int[] employeeSlot = findIndexOf(employee);
        if (employeeSlot != null) {
            Employee removedEmployee = getAtIndex(employeeSlot[0], employeeSlot[1]);
            deleteAtIndex(employeeSlot[0], employeeSlot[1]);
            shrink(rowCount, columnCount);
        }
    }

    /**
     * Modifies an existing employee in the schedule.
     *
     * @param oldEmployee the employee to modify
     * @param newEmployee the new employee data
     */
    public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
        int[] employeeSlot = findIndexOf(oldEmployee);
        if (employeeSlot != null)
            setAtIndex(employeeSlot[0], employeeSlot[1], newEmployee);
    }

    /**
     * Prints all employees in the schedule.
     *
     * @return a string representation of all employees in the schedule
     */
    public String printAll() {
        StringBuilder output = new StringBuilder();
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                Employee employee = getAtIndex(row, column);
                if (employee != null) {
                    output.append("Row: ").append(row).append(", Column: ").append(column)
                            .append(" - ").append(employee.getName()).append("\n");
                }
            }
        }
        return output.toString();
    }

    /**
     * Checks if the schedule is empty.
     *
     * @return true if the schedule is empty, false otherwise
     */
    public boolean isEmpty() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (getAtIndex(row, column) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Finds the index of the specified employee in the schedule.
     *
     * @param employee the employee to find
     * @return an array containing the row and column index of the employee, or null if not found
     */
    private int[] findIndexOf(Employee employee) {
        int[] index = new int[2];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (employee == schedule[row][column]) {
                    index[0] = row;
                    index[1] = column;
                    return index;
                }
            }
        }
        return null;
    }

    /**
     * Deletes the employee at the specified index in the schedule.
     *
     * @param row    the row index of the employee
     * @param column the column index of the employee
     */
    private void deleteAtIndex(int row, int column) {
        if (isValidIndex(row, column)) {
            schedule[row][column] = null;
        }
    }

    /**
     * Expands the schedule to the specified new row and column sizes.
     *
     * @param newRowSize    the new number of rows
     * @param newColumnSize the new number of columns
     */
    private void expand(int newRowSize, int newColumnSize) {
        Employee[][] expandedSchedule = new Employee[newRowSize][newColumnSize];
        for (int row = 0; row < rowCount; row++) {
            System.arraycopy(schedule[row], 0, expandedSchedule[row], 0, columnCount);
        }
        schedule = expandedSchedule;
        rowCount = newRowSize;
        columnCount = newColumnSize;
    }

    /**
     * Shrinks the schedule to the specified new row and column sizes.
     *
     * @param newRowSize    the new number of rows
     * @param newColumnSize the new number of columns
     */
    private void shrink(int newRowSize, int newColumnSize) {
        Employee[][] shrunkSchedule = new Employee[newRowSize][newColumnSize];
        for (int row = 0; row < newRowSize; row++) {
            if (row < rowCount) {
                System.arraycopy(schedule[row], 0, shrunkSchedule[row], 0, newColumnSize);
            }
        }
        schedule = shrunkSchedule;
        rowCount = newRowSize;
        columnCount = newColumnSize;
    }

    /**
     * Checks if the specified row and column indices are valid within the schedule.
     *
     * @param row    the row index to check
     * @param column the column index to check
     * @return true if the indices are valid, false otherwise
     */
    private boolean isValidIndex(int row, int column) {
        return row >= 0 && row < rowCount && column >= 0 && column < columnCount;
    }
}