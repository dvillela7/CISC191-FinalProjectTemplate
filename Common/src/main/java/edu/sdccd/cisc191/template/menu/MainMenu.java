package edu.sdccd.cisc191.template.menu;

import java.util.ArrayList;
import java.util.List;

// MODULE 6

public class MainMenu implements MenuComponent {
    private List<String> menuItems;

    public MainMenu() {
        menuItems = new ArrayList<>();
    }

    @Override
    public void add(String component) {
        menuItems.add(component);
    }

    @Override
    public List<String> getItems() {
        return menuItems;
    }
}
