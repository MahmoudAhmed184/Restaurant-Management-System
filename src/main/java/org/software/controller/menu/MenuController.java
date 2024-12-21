package org.software.controller.menu;

import org.software.model.factories.MenuItemFactory;
import org.software.model.menu.MenuItem;
import org.software.model.repositories.MenuItemRepository;
import org.software.utils.MenuItemMapper;
import org.software.view.menumanagement.EditItemPage;
import org.software.view.menumanagement.MenuManagementPanel;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

import org.software.view.menumanagement.MenuManagementAddItemFrame;

public class MenuController {
    private final MenuItemRepository menuItemRepository;
    private final MenuManagementPanel menuManagementPanel;

    public MenuController() {
        this.menuItemRepository = new MenuItemRepository();
        this.menuManagementPanel = MenuManagementPanel.getInstance();
    }

    public void initialize() {
        registerActionListener();
        loadMenuItemsTableData();
    }

    private void registerActionListener() {
        menuManagementPanel.addAddButtonActionListener(e -> handleAddItem());
        menuManagementPanel.addEditButtonActionListener(e -> handleEditItem());
        menuManagementPanel.addDeleteButtonActionListener(e -> handleDeleteItem());
    }

    private void loadMenuItemsTableData() {
        List<MenuItem> menuItems = menuItemRepository.getAll();
        String[][] data = MenuItemMapper.mapMenuItemsData(menuItems);
        menuManagementPanel.updateMenuTable(new String[]{"ID", "Name", "Price", "Category"}, data);
    }

    private void handleAddItem() {
        MenuManagementAddItemFrame addItemForm = new MenuManagementAddItemFrame();

        addItemForm.setSubmitButtonListener(e -> handleSubmitMenuItemForm(addItemForm));
        addItemForm.show();
    }

    private void handleSubmitMenuItemForm(MenuManagementAddItemFrame addItemForm) {
        String name = addItemForm.getItemName();
        String category = addItemForm.getCategory();
        String price = addItemForm.getPrice();

        if (name.isEmpty() || category.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled out.");
            return;
        }

        try {
            MenuItem menuItem = menuItemRepository.create(MenuItemFactory.getMenuItem(category, -1, name, new BigDecimal(price)));
            menuManagementPanel.addRowToMenuTable(MenuItemMapper.mapMenuItemsData(menuItem));
            addItemForm.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handleEditItem() {
        int selectedRow = menuManagementPanel.getSelectedItem();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select an item to edit.");
            return;
        }

        MenuItem selectedItem = getMenuItemDetailsFromTable(selectedRow);

        EditItemPage editItemPage = new EditItemPage(
            selectedItem.name(),
            selectedItem.category(),
            selectedItem.price().doubleValue()
        );

        editItemPage.setSaveButtonListener(e -> {
            try {
                String updatedName = editItemPage.getUpdatedName();
                String updatedCategory = editItemPage.getUpdatedCategory();
                double updatedPrice = editItemPage.getUpdatedPrice();
                BigDecimal updatedPriceBigDecimal = BigDecimal.valueOf(updatedPrice);
                MenuItem menuItem = MenuItemFactory.getMenuItem(updatedCategory, selectedItem.id(), updatedName, updatedPriceBigDecimal);
                menuItemRepository.update(menuItem);

                menuManagementPanel.updateRowInMenuTable(selectedRow, MenuItemMapper.mapMenuItemsData(menuItem));

                JOptionPane.showMessageDialog(null, "Item updated successfully!");
                editItemPage.close();
            } catch (NumberFormatException ex) {
                editItemPage.showError("Invalid price format! Please enter a valid number.");
            }
        });

        editItemPage.setCancelButtonListener(e -> editItemPage.close());

        editItemPage.setVisible(true);
    }

    private MenuItem getMenuItemDetailsFromTable(int row) {
        int itemId = Integer.parseInt(menuManagementPanel.getMenuTableValueAt(row, 0));
        String itemName = menuManagementPanel.getMenuTableValueAt(row, 1);
        String price = menuManagementPanel.getMenuTableValueAt(row, 2);
        String category = menuManagementPanel.getMenuTableValueAt(row, 3);
        return MenuItemFactory.getMenuItem(category, itemId, itemName, new BigDecimal(price));
    }


    private void handleDeleteItem() {
        int selectedRow = menuManagementPanel.getSelectedItem();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select an item to delete.");
            return;
        }
        MenuItem menuItem = getMenuItemDetailsFromTable(selectedRow);
        menuItemRepository.delete(menuItem);
        menuManagementPanel.removeRowInMenuTable(selectedRow);
        JOptionPane.showMessageDialog(null, "Item deleted successfully!");
    }

}
