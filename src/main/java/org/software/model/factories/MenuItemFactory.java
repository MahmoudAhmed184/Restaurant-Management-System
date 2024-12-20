package org.software.model.factories;

import org.software.model.menu.MenuItem;

import java.math.BigDecimal;

public class MenuItemFactory {
    private MenuItemFactory() {}

    public static MenuItem getMenuItem(String category, int id, String name, BigDecimal price) {
        return switch (category.toLowerCase()) {
            case "dessert", "appetizer", "main course" -> new MenuItem(id, name, price, category);
            default -> throw new IllegalArgumentException("Unknown menu item type: " + category);
        };
    }
}
