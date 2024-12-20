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
    private JButton viewOrderButton;

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
        buttonPanel.add(viewOrderButton = new JButton("View Order"));
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

    public void addViewOrderButtonActionListener(ActionListener listener) {
        viewOrderButton.addActionListener(listener);
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
}