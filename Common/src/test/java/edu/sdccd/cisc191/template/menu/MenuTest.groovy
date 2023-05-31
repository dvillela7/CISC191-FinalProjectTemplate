package edu.sdccd.cisc191.template.menu

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        MenuComponent mainMenu = new MainMenu();
        mainMenu.addItem("Item 1");
        mainMenu.addItem("Item 2");
        menu.addMenuComponent("Main", mainMenu);
    }

    @Test
    void testAddMenuComponent() {
        MenuComponent drinkMenu = new DrinkMenu();
        drinkMenu.addItem("Drink 1");
        drinkMenu.addItem("Drink 2");
        menu.addMenuComponent("Drinks", drinkMenu);

        assertTrue(menu.containsDrinkMenu());
    }

    @Test
    void testRemoveMenuComponent() {
        menu.removeMenuComponent("Main");
        assertFalse(menu.containsMainMenu());
    }

    @Test
    void testDisplayMenu() {
        String expectedMenu = "\nMain Menu\nItem 1\nItem 2\n----------\n";
        String actualMenu = menu.displayMenu();
        assertEquals(expectedMenu, actualMenu);
    }

    @Test
    void testContainsMainMenu() {
        assertTrue(menu.containsMainMenu());
    }

    @Test
    void testContainsDrinkMenu() {
        assertFalse(menu.containsDrinkMenu());
    }
}
