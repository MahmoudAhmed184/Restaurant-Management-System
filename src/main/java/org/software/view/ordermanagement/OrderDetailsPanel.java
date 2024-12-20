package org.software.view.ordermanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderDetailsPanel extends JPanel {
    private JButton addButton;
    private JButton removeButton;
    private JButton confirmButton;
    private final JTable orderDetailsTable;
    private final DefaultTableModel orderDetailsTableModel;
    private final OrderTypePanel orderTypePanel;

    public OrderDetailsPanel() {
        this.setLayout(new BorderLayout());
        orderDetailsTable = new JTable(orderDetailsTableModel = new DefaultTableModel());
        JPanel orderActionsPanel = getOrderActionsPanel();
        orderTypePanel = new OrderTypePanel();
        this.add(orderTypePanel, BorderLayout.NORTH);
        this.add(new JScrollPane(orderDetailsTable), BorderLayout.CENTER);
        this.add(orderActionsPanel, BorderLayout.SOUTH);
    }

    private JPanel getOrderActionsPanel() {
        JPanel orderActionsPanel = new JPanel();
        orderActionsPanel.add(addButton = new JButton("Add to Order"));
        orderActionsPanel.add(removeButton = new JButton("Remove"));
        orderActionsPanel.add(confirmButton = new JButton("Confirm Order"));
        return orderActionsPanel;
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

    public int getSelectedOrderTableRow() {
        return orderDetailsTable.getSelectedRow();
    }

    public String getOrderTableValueAt(int row, int column) {
        return orderDetailsTable.getValueAt(row, column).toString();
    }

    public String getSelectedOrderType() {
        return orderTypePanel.getSelectedOrderType();
    }

    public String getTableNumber() {
        return orderTypePanel.getTableNumber();
    }
}
