package edu.sdccd.cisc191.template.menu;

// MODULE 6

import java.util.List;

interface MenuComponent {
    /**
     * Adds a component to the menu.
     *
     * @param component the component to add
     */
    void add(String component);

    /**
     * Retrieves a list of items in the menu.
     *
     * @return the list of menu items
     */
    List<String> getItems();
}
