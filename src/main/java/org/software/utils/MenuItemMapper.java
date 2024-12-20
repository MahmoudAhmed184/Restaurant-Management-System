package org.software.utils;

import org.software.model.menu.MenuItem;

import java.util.List;

public class MenuItemMapper {
    private MenuItemMapper() {
    }

    public static String[][] mapOrderData(List<MenuItem> menuItems) {
        return menuItems.stream()
                .map(MenuItemMapper::mapOrderData)
                .toArray(String[][]::new);
    }

    public static String[] mapOrderData(MenuItem menuItem) {
        return new String[]{
                String.valueOf(menuItem.id()),
                menuItem.name(),
                String.valueOf(menuItem.price()),
                menuItem.category()
        };
    }
}