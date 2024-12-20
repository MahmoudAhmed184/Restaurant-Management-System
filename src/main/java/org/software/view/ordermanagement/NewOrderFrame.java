package org.software.view.ordermanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewOrderFrame extends JFrame {
    private final MenuPanel menuPanel;
    private final OrderDetailsPanel orderDetailsPanel;

    public NewOrderFrame() {
        setTitle("Add New Order");
        setSize(800, 400);
        setLocationRelativeTo(null);
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(300);
        menuPanel = new MenuPanel();
        orderDetailsPanel = new OrderDetailsPanel();
        splitPane.setLeftComponent(menuPanel);
        splitPane.setRightComponent(orderDetailsPanel);
        add(splitPane, BorderLayout.CENTER);
    }

    public void updateMenuTable(String[] menuColumns, String[][] menuData) {
        menuPanel.updateMenuTable(menuColumns, menuData);
    }

    public void updateOrderDetails(String[] orderColumns, String[][] orderData) {
        orderDetailsPanel.updateOrderDetails(orderColumns, orderData);
    }

    public void addAddButtonActionListener(ActionListener listener) {
        orderDetailsPanel.addAddButtonActionListener(listener);
    }

    public void addRemoveButtonActionListener(ActionListener listener) {
        orderDetailsPanel.addRemoveButtonActionListener(listener);
    }

    public void addConfirmButtonActionListener(ActionListener listener) {
        orderDetailsPanel.addConfirmButtonActionListener(listener);
    }

    public int getSelectedMenuTableRow() {
        return menuPanel.getSelectedMenuTableRow();
    }

    public void displayMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getMenuTableValueAt(int row, int column) {
        return menuPanel.getMenuTableValueAt(row, column);
    }

    public int getSelectedOrderTableRow() {
        return orderDetailsPanel.getSelectedOrderTableRow();
    }

    public String getOrderTableValueAt(int row, int column) {
        return orderDetailsPanel.getOrderTableValueAt(row, column);
    }

    public String getSelectedOrderType() {
        return orderDetailsPanel.getSelectedOrderType();
    }

    public String getTableNumber() {
        return orderDetailsPanel.getTableNumber();
    }
}
