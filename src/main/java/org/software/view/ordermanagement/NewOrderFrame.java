package org.software.view.ordermanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class NewOrderFrame extends JFrame {
    private final DefaultTableModel orderDetailsTableModel;
    private final JTable orderDetailsTable;
    private final DefaultTableModel menuTableModel;
    private final JTable menuTable;
    private final JButton addButton;
    private final JButton removeButton;
    private final JButton confirmButton;

    public NewOrderFrame() {
        setTitle("Add New Order");
        setSize(800, 400);
        setLocationRelativeTo(null);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(300);

        // Left Panel - Menu Section
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuTable = new JTable(menuTableModel = new DefaultTableModel());
        menuPanel.add(new JScrollPane(menuTable), BorderLayout.CENTER);

        // Right Panel - Menu Details Section
        JPanel orderDetailsPanel = new JPanel(new BorderLayout());
        orderDetailsTable = new JTable(orderDetailsTableModel = new DefaultTableModel());
        orderDetailsPanel.add(new JScrollPane(orderDetailsTable), BorderLayout.CENTER);

        JPanel orderActionsPanel = new JPanel();
        orderActionsPanel.add(addButton = new JButton("Add to Order"));
        orderActionsPanel.add(removeButton = new JButton("Remove"));
        orderActionsPanel.add(confirmButton = new JButton("Confirm Order"));

        orderDetailsPanel.add(orderActionsPanel, BorderLayout.SOUTH);

        // Add panels to split pane
        splitPane.setLeftComponent(menuPanel);
        splitPane.setRightComponent(orderDetailsPanel);

        add(splitPane);
    }

    public void updateMenuTable(String[] menuColumns, String[][] menuData) {
        menuTableModel.setDataVector(menuData, menuColumns);
    }

    public void updateOrderDetails(String[] orderColumns, String[][] orderData) {
        orderDetailsTableModel.setDataVector(orderData, orderColumns);
    }
    
    public void addAddButtonActionListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
    
    public void addRemoveButtonActionListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }
    
    public void addConfirmButtonActionListener(ActionListener listener) {
        confirmButton.addActionListener(listener);
    }

    public int getSelectedMenuTableRow() {
        return menuTable.getSelectedRow();
    }

    public void displayMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getMenuTableValueAt(int row, int column) {
        return menuTable.getValueAt(row, column).toString();
    }

    public int getSelectedOrderTableRow() {
        return orderDetailsTable.getSelectedRow();
    }

    public String getOrderTableValueAt(int row, int column) {
        return orderDetailsTable.getValueAt(row, column).toString();
    }

    public void addWindowListener(WindowAdapter windowAdapter) {
        super.addWindowListener(windowAdapter);
    }
}

