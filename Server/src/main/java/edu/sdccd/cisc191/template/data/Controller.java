package edu.sdccd.cisc191.template.data;

import edu.sdccd.cisc191.template.Request;
import edu.sdccd.cisc191.template.Response;
import edu.sdccd.cisc191.template.employee.*;
import edu.sdccd.cisc191.template.franchise.Franchise;
import edu.sdccd.cisc191.template.franchise.FranchiseList;
import edu.sdccd.cisc191.template.franchise.FranchiseSorter;
import edu.sdccd.cisc191.template.menu.DrinkMenu;
import edu.sdccd.cisc191.template.menu.MainMenu;
import edu.sdccd.cisc191.template.menu.Menu;

public class Controller {

    /**
     * Interprets the given request and generates a response based on the request parameters.
     *
     * @param req The request object containing the necessary information.
     * @return The response generated based on the request.
     */
    protected static Response interpret(Request req) {
        String name = req.getName();
        ClientData clientData = null;
        if (!ClientData.datas.containsKey(name)) {
            clientData = new ClientData(name);
            ClientData.datas.put(name, clientData);
        }
        else
            clientData = ClientData.datas.get(req.getName());

        String id = req.getId().substring(0, req.getId().indexOf(":"));
        String action = req.getId().substring(req.getId().indexOf(":") + 1);
        String[] vals = req.getVal();
        String msg = "";
        EmployeeRoster employees = clientData.getEmployeeRoster();
        EmployeeSchedule schedule = clientData.getEmployeeSchedule();
        switch (id) {
            case "franchise":
                FranchiseList<Franchise> franchises = clientData.getFranchiseList();
                switch (action) {
                    case "start":
                        Franchise f = new Franchise(vals[0], vals[1]);
                        if (FranchiseSorter.searchByLocation(franchises, vals[1]) != null)
                            msg = "There's already a store there!";
                        else {
                            franchises.add(f);
                            msg = "Successfully started a franchise in " + vals[1] + "!";
                        }
                        break;
                    case "end":
                        Franchise searched = FranchiseSorter.searchByLocation(franchises, vals[0]);
                        if (searched != null) {
                            franchises.remove(searched);
                            msg = "Successfully bulldozed the franchise in " + vals[0] + "!";
                            if (franchises.head == null) {
                                ClientData.datas.remove(name);
                                ClientData.datas.put(name, new ClientData(name));
                                msg = "You closed all your franchises! No menu, employees, or anything left!";
                            }
                        } else
                            msg = "Franchise not found!";
                        break;
                    case "list":
                        FranchiseSorter.sortByLocation(franchises);
                        msg = msg.concat("\nFranchises\n");
                        for (Franchise franchise : franchises.toArrayList()) {
                            msg = msg.concat("Franchise Manager: " + franchise.getName() + "\n");
                            msg = msg.concat("Location: " + franchise.getLocation() + "\n");
                            msg = msg.concat("--------------------" + "\n");
                        }
                        break;
                    case "exists":
                        if (franchises.head == null)
                            msg = "NONE";
                        else
                            msg ="EXISTS";
                        break;
                }
                break;
            case "employee":
                switch (action) {
                    case "hire":
                        Employee e;
                        if (vals[2].equalsIgnoreCase("cashier"))
                            e = new Cashier(vals[0], Double.parseDouble(vals[1]), Double.parseDouble(vals[3]));
                        else
                            e = new Cook(vals[0], Double.parseDouble(vals[1]), Double.parseDouble(vals[3]));

                        employees.hireEmployee(e);
                        msg = "Successfully hired " + vals[0] + " to be a " + vals[2] + "!";
                        break;
                    case "fire":
                        Employee fired = employees.searchByName(vals[0]);
                        if (fired == null)
                            msg = "Employee not found!";
                        else {
                            employees.fireEmployee(fired);
                            for (int i = 0; i < 5; i++)
                                schedule.removeEmployee(fired);
                            msg = "Successfully fired " + vals[0] + "!";
                        }
                        break;
                    case "list":
                        if (employees.isEmpty())
                            msg = "You have no employees!";
                        else {
                            msg = msg.concat("\nEmployees\n");
                            msg = msg.concat(employees.printAll());
                        }
                        break;
                }
                break;
            case "schedule":
                switch (action) {
                    case "add":
                        if (employees.isEmpty())
                            return new Response("You have no employees!");
                        Employee emp = employees.searchByName(vals[0]);
                        if (emp == null)
                            msg = "Employee not found!";
                        else {
                            schedule.addEmployee(emp);
                            msg = "Successfully added " + vals[0] + " to the schedule!";
                        }
                        break;
                    case "remove":
                        if (employees.isEmpty())
                            return new Response("You have no employees!");
                        Employee removed = employees.searchByName(vals[0]);
                        if (removed == null)
                            msg = "Employee not found!";
                        else {
                            schedule.removeEmployee(removed);
                            msg = "Successfully removed " + vals[0] + " from the schedule!";
                        }
                        break;
                    case "modify":
                        if (employees.isEmpty())
                            return new Response("You have no employees!");
                        else if (employees.hasOneEmployee())
                            return new Response("You only have 1 employee on schedule!");
                        Employee emp1 = employees.searchByName(vals[0]);
                        if (emp1 == null)
                            return new Response("First employee not found!");
                        Employee emp2 = employees.searchByName(vals[1]);
                        if (emp2 == null)
                            return new Response("Second employee not found!");
                        if (emp1.equals(emp2))
                            msg = "Those two are the same employees!";
                        else {
                            schedule.modifyEmployee(emp1, emp2);
                            msg = "Successfully swapped " + vals[0] + " and " + vals[1] + "!";
                        }
                        break;
                    case "list":
                        if (employees.isEmpty())
                            msg = "You have no employees!";
                        else if (schedule.isEmpty())
                            msg = "You have no employees on the schedule!";
                        else
                            msg = schedule.printAll();
                        break;
                }
                break;
            case "menu":
                Menu menu = clientData.getMenu();
                switch (action) {
                    case "create":
                        if (vals[0].equalsIgnoreCase("main")) {
                            if (menu.containsMainMenu())
                                return new Response("There is already a main menu!");
                            MainMenu mm = new MainMenu();
                            for (int i = 1; i < vals.length; i++)
                                mm.add(vals[i]);
                            menu.addMenuComponent("Main", mm);
                            msg = "Main menu successfully created!";
                        } else {
                            if (menu.containsDrinkMenu())
                                return new Response("There is already a drink menu!");
                            DrinkMenu dm = new DrinkMenu();
                            for (int i = 1; i < vals.length; i++)
                                dm.add(vals[i]);
                            menu.addMenuComponent("Drinks", dm);
                            msg = "Drink menu successfully created!";
                        }
                        break;
                    case "remove":
                        if (vals[0].equalsIgnoreCase("main")) {
                            if (!menu.containsMainMenu())
                                return new Response("There is no main menu!");
                            menu.removeMenuComponent("Main");
                            msg = "Main menu successfully removed!";
                        } else {
                            if (!menu.containsDrinkMenu())
                                return new Response("There is no drink menu!");
                            menu.removeMenuComponent("Drinks");
                            msg = "Drink menu successfully removed!";
                        }
                        break;
                    case "display":
                        if (menu.containsDrinkMenu() || menu.containsMainMenu())
                            msg = menu.displayMenu();
                        else
                            msg = "You have no menus!";
                        break;
                }
                break;
            default:
                msg = "PASS";
                break;
        }
        return new Response(msg);
    }
}
