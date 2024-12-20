package org.software.view.menumanagement;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MenuManagementPanel extends JPanel {
    private static MenuManagementPanel instance;
    private final DefaultTableModel menuTableModel;
    private final JTable menuTable;
    private final JButton addButton;
    private final JButton editButton;
    private final JButton deleteButton;

    private MenuManagementPanel() {
        setLayout(new BorderLayout());

        // Menu Table
        String[] columnNames = {"Item Name", "Category", "Price", "Availability"};
        Object[][] data = {
                {"Burger", "Main Course", "5.99", "Available"},
                {"Cake", "Dessert", "3.50", "Out of Stock"}
        };

        menuTableModel = new DefaultTableModel(data, columnNames);
        menuTable = new JTable(menuTableModel);
        JScrollPane tableScrollPane = new JScrollPane(menuTable);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton = new JButton("Add Item"));
        buttonPanel.add(editButton = new JButton("Edit Item"));
        buttonPanel.add(deleteButton = new JButton("Delete Item"));

        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static MenuManagementPanel getInstance() {
        if (instance == null) {
            instance = new MenuManagementPanel();
        }
        return instance;
    }
}

