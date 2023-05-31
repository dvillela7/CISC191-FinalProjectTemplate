package edu.sdccd.cisc191.template.menu;

import java.util.HashMap;
import java.util.Map;

// MODULE 9

public class Menu {
    private Map<String, MenuComponent> components;

    public Menu() {
        components = new HashMap<>();
    }

    /**
     * Adds a menu component to the menu.
     *
     * @param key       the key associated with the component
     * @param component the menu component to add
     */
    public void addMenuComponent(String key, MenuComponent component) {
        components.put(key, component);
    }

    /**
     * Removes a menu component from the menu.
     *
     * @param key the key associated with the component to remove
     */
    public void removeMenuComponent(String key) {
        components.remove(key);
    }

    /**
     * Displays the menu as a formatted string.
     *
     * @return the formatted menu string
     */
    public String displayMenu() {
        String display = "";
        if (containsMainMenu()) {
            display = display.concat("\nMain Menu\n");
            for (String item : components.get("Main").getItems()) {
                display = display.concat(item) + "\n";
            }
            display = display.concat("----------\n");
        }
        if (containsDrinkMenu()) {
            display = display.concat("\nDrink Menu\n");
            for (String item : components.get("Drinks").getItems()) {
                display = display.concat(item) + "\n";
            }
            display = display.concat("----------\n");
        }
        return display;
    }

    /**
     * Checks if the menu contains the main menu.
     *
     * @return true if the main menu exists, false otherwise
     */
    public boolean containsMainMenu() {
        return components.containsKey("Main");
    }

    /**
     * Checks if the menu contains the drink menu.
     *
     * @return true if the drink menu exists, false otherwise
     */
    public boolean containsDrinkMenu() {
        return components.containsKey("Drinks");
    }
}