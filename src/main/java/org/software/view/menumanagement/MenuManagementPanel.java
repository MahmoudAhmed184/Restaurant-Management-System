package org.software.view.menumanagement;

import java.awt.*;
import java.awt.event.ActionListener;

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

        menuTableModel = new DefaultTableModel();
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

    public void updateRowInMenuTable(int row,String[] data){
        menuTableModel.removeRow(row);
        menuTableModel.insertRow(row,data);
    }

    public void removeRowInMenuTable(int row){
        menuTableModel.removeRow(row);
    }

    public void updateMenuTable(String[] header, String[][] data) {
        menuTableModel.setDataVector(data, header);
    }

    public void addRowToMenuTable(String[] rowData) {
        menuTableModel.addRow(rowData);
    }

    public String getMenuTableValueAt(int row, int column) {
        return menuTable.getValueAt(row, column).toString();
    }

    public int getSelectedItem() {
        return menuTable.getSelectedRow();
    }

    public void addAddButtonActionListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
    public void addEditButtonActionListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }
    public void addDeleteButtonActionListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
}

