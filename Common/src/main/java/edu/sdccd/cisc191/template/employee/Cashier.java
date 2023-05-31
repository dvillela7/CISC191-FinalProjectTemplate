package edu.sdccd.cisc191.template.employee;

public class Cashier extends Employee {
    private double bonusPercentage;

    public Cashier(String name, double salary, double bonusPercentage) {
        super(name, salary);
        this.bonusPercentage = bonusPercentage;
    }
}