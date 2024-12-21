package org.software.view.ordermanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderManagementPanel extends JPanel {
    private static OrderManagementPanel instance;
    private final JTable ordersTable;
    private final DefaultTableModel ordersTableModel;
    private JButton addOrderButton;
    private JButton checkoutOrderButton;
    private JButton cancelOrderButton;

    private OrderManagementPanel() {
        setLayout(new BorderLayout());
        ordersTableModel = new DefaultTableModel();
        JScrollPane tableScrollPane = new JScrollPane(ordersTable = new JTable(ordersTableModel));
        JPanel buttonPanel = createButtonPanel();
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static OrderManagementPanel getInstance() {
        if (instance == null) {
            instance = new OrderManagementPanel();
        }
        return instance;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addOrderButton = new JButton("Add New Order"));
        buttonPanel.add(checkoutOrderButton = new JButton("Checkout Order"));
        buttonPanel.add(cancelOrderButton = new JButton("Cancel Order"));
        return buttonPanel;
    }

    public void updateOrdersTable(String[] headers, String[][] data) {
        ordersTableModel.setDataVector(data, headers);
    }

    public void addRowToTable(String[] rowData) {
        ordersTableModel.addRow(rowData);
    }

    public void addAddOrderButtonActionListener(ActionListener listener) {
        addOrderButton.addActionListener(listener);
    }

    public void addCheckoutOrderButtonActionListener(ActionListener listener) {
        checkoutOrderButton.addActionListener(listener);
    }

    public void addCancelOrderButtonActionListener(ActionListener listener) {
        cancelOrderButton.addActionListener(listener);
    }

    public String getOrderTableValueAt(int row, int column) {
        return ordersTableModel.getValueAt(row, column).toString();
    }

    public int getSelectedRow() {
        return ordersTable.getSelectedRow();
    }

    public void displayMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public NewOrderFrame createNewOrderFrame() {
        return new NewOrderFrame();
    }

    public void removeRowFromOrdersTable(int row) {
        ordersTableModel.removeRow(row);
    }
}