package org.software.view.payment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PaymentPanel extends JPanel {

    private static PaymentPanel instance;
    private final DefaultTableModel ordersTableModel;
    private final JTable ordersTable;
    private final JButton confirmButton;
    private final JButton cancelButton;

    private PaymentPanel() {
        setLayout(new BorderLayout());

        // Menu Table
        String[] columnNames = {"Name", "Quantity", "Price", "SubTotal"};
        Object[][] data = {
                {"Burger", "1", "5.99", "5.99"},
                {"Cake", "2", "3.50", "7"}
        };

        ordersTableModel = new DefaultTableModel(data, columnNames);
        ordersTable = new JTable(ordersTableModel);
        JScrollPane tableScrollPane = new JScrollPane(ordersTable);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton = new JButton("Confirm Payment"));
        buttonPanel.add(cancelButton = new JButton("Cancel"));

        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static PaymentPanel getInstance() {
        if (instance == null) {
            instance = new PaymentPanel();
        }
        return instance;
    }

}
