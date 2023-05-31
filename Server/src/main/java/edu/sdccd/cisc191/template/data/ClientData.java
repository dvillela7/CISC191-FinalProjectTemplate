package edu.sdccd.cisc191.template.data;

import edu.sdccd.cisc191.template.employee.EmployeeRoster;
import edu.sdccd.cisc191.template.employee.EmployeeSchedule;
import edu.sdccd.cisc191.template.franchise.Franchise;
import edu.sdccd.cisc191.template.franchise.FranchiseList;
import edu.sdccd.cisc191.template.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class ClientData {
    public static Map<String, ClientData> datas = new HashMap<>();

    private FranchiseList<Franchise> franchiseList;
    private Menu menu;
    private EmployeeRoster employeeRoster;
    private EmployeeSchedule employeeSchedule;
    private String name;

    public ClientData(String name) {
        this.name = name;
        franchiseList = new FranchiseList<>();
        menu = new Menu();
        employeeRoster = new EmployeeRoster();
        employeeSchedule = new EmployeeSchedule(5, 7);
    }

    public FranchiseList<Franchise> getFranchiseList() {
        return franchiseList;
    }

    public Menu getMenu() {
        return menu;
    }

    public EmployeeRoster getEmployeeRoster() {
        return employeeRoster;
    }

    public EmployeeSchedule getEmployeeSchedule() {
        return employeeSchedule;
    }
}