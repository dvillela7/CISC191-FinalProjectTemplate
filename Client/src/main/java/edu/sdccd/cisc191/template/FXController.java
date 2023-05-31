package edu.sdccd.cisc191.template;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXController {

    @FXML
    private Label managerPanelTitle;

    @FXML
    private Label employeesLabel;

    @FXML
    private Button hireButton;

    @FXML
    private Button fireButton;

    @FXML
    private Button listEmployeesButton;

    @FXML
    private Label scheduleLabel;

    @FXML
    private Button scheduleAddButton;

    @FXML
    private Button scheduleRemoveButton;

    @FXML
    private Button modifyScheduleButton;

    @FXML
    private Button listScheduleButton;

    @FXML
    private Label menuLabel;

    @FXML
    private Button createMenuButton;

    @FXML
    private Button removeMenuButton;

    @FXML
    private Button displayMenusButton;

    @FXML
    private Label franchiseLabel;

    @FXML
    private Button startFranchiseButton;

    @FXML
    private Button endFranchiseButton;

    @FXML
    private Button listFranchisesButton;

    /**
     * Creates a menu based on user input.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during menu creation
     */
    @FXML
    void createMenu(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        String type = Asker.answer("What kind of menu (main/drink): ");
        if (type.equalsIgnoreCase("main")) {
            type = "main";
            System.out.println("Main selected");
        } else {
            type = "drink";
            System.out.println("Drink selected");
        }
        int num = Asker.answerInt("How many items would you like to add: ") + 1;
        if (num <= 1) {
            System.out.println("Too low!");
            return;
        }
        String[] items = new String[num];
        items[0] = type;
        for (int i = 1; i < num; i++)
            items[i] = Asker.answer("Item " + i + ": ");
        System.out.println(Client.sendRequest("menu:create", items).getMsg());
    }

    /**
     * Displays the menus of the franchise.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during menu display
     */
    @FXML
    void displayMenus(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        System.out.println(Client.sendRequest("menu:display", new String[] {""}).getMsg());
    }

    /**
     * Ends a franchise based on user input.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during franchise termination
     */
    @FXML
    void endFranchise(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        String location = Asker.answer("Where is it: ");
        System.out.println(Client.sendRequest("franchise:end", new String[] {location}).getMsg());
    }

    /**
     * Fires an employee from the franchise based on user input.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during employee termination
     */
    @FXML
    void fireEmployee(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        String name = Asker.answer("Name of employee: ");
        System.out.println(Client.sendRequest("employee:fire", new String[] {name}).getMsg());
    }

    /**
     * Hires an employee for the franchise based on user input.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during employee hiring
     */
    @FXML
    void hireEmployee(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        String name = Asker.answer("Name of employee: ");
        double salary = Asker.answerDouble("Employee's salary: ");
        String job = Asker.answer("What job? (cashier/cook): ");
        double extra;
        if (job.equalsIgnoreCase("cashier"))
            extra = Asker.answerDouble("Employee's bonus percentage: ");
        else
            extra = Asker.answerDouble("Employee's overtime rate: ");
        System.out.println(Client.sendRequest("employee:hire", new String[] {name, salary + "", job, extra + ""}).getMsg());
    }

    /**
     * Lists all the franchises.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during franchise listing
     */
    @FXML
    void listFranchises(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        System.out.println(Client.sendRequest("franchise:list", new String[] {""}).getMsg());
    }

    /**
     * Lists all the employees in the franchise.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during employee listing
     */
    @FXML
    void listEmployees(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        System.out.println(Client.sendRequest("employee:list", new String[] {""}).getMsg());
    }

    /**
     * Lists the schedule of the franchise.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during schedule listing
     */
    @FXML
    void listSchedule(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        System.out.println(Client.sendRequest("schedule:list", new String[] {""}).getMsg());
    }

    /**
     * Modifies the schedule of the franchise based on user input.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during schedule modification
     */
    @FXML
    void modifySchedule(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        String first = Asker.answer("Name of first employee: ");
        String second = Asker.answer("Name of second employee");
        System.out.println(Client.sendRequest("schedule:modify", new String[] {first, second}).getMsg());
    }

    /**
     * Removes a menu from the franchise based on user input.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during menu removal
     */
    @FXML
    void removeMenu(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        String type = Asker.answer("Which menu would you like to remove (main/drink): ");
        if (type.equalsIgnoreCase("main"))
            type = "main";
        else
            type = "drink";

        System.out.println(Client.sendRequest("menu:remove", new String[] {type}).getMsg());
    }

    /**
     * Adds an employee to the schedule based on user input.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during schedule addition
     */
    @FXML
    void scheduleAdd(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        String name = Asker.answer("Name of employee: ");
        System.out.println(Client.sendRequest("schedule:add", new String[] {name}).getMsg());
    }

    /**
     * Removes an employee from the schedule based on user input.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during schedule removal
     */
    @FXML
    void scheduleRemove(ActionEvent event) throws Exception {
        if (!franchiseExists()) {
            System.out.println("Create a franchise first!");
            return;
        }
        String name = Asker.answer("Name of employee: ");
        System.out.println(Client.sendRequest("schedule:remove", new String[] {name}).getMsg());
    }

    /**
     * Starts a new franchise based on user input.
     *
     * @param event the ActionEvent triggering the method
     * @throws Exception if an exception occurs during franchise startup
     */
    @FXML
    void startFranchise(ActionEvent event) throws Exception {
        String name = Asker.answer("Name of the manager in charge:");
        String location = Asker.answer("Location of the building: ");
        System.out.println(Client.sendRequest("franchise:start", new String[] {name, location}).getMsg());
    }

    /**
     * Checks if a franchise already exists.
     *
     * @return true if the franchise exists, false otherwise
     * @throws Exception if an exception occurs during the existence check
     */
    static boolean franchiseExists() throws Exception {
        return Client.sendRequest("franchise:exists", new String[] {""}).getMsg().equals("EXISTS");
    }
}