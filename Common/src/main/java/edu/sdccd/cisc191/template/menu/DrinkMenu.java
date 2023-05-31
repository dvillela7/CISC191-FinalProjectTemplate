package edu.sdccd.cisc191.template.menu;

import java.util.ArrayList;
import java.util.List;

// MODULE 6

public class DrinkMenu implements MenuComponent {
    private List<String> drinks;

    public DrinkMenu() {
        drinks = new ArrayList<>();
    }

    @Override
    public void add(String component) {
        drinks.add(component);
    }

    @Override
    public List<String> getItems() {
        return drinks;
    }
}