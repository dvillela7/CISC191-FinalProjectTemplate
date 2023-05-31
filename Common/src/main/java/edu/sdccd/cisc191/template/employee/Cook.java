package edu.sdccd.cisc191.template.employee;

public class Cook extends Employee {
    private double overtimeRate;

    public Cook(String name, double salary, double overtimeRate) {
        super(name, salary);
        this.overtimeRate = overtimeRate;
    }
}
