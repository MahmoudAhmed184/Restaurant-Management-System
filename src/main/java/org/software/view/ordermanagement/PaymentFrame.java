package org.software.view.ordermanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class PaymentFrame extends JFrame {
    private final DefaultTableModel ordersTableModel;
    private JLabel totalPriceLabel;
    private final JTable ordersTable;
    private JButton checkoutButton;

    public PaymentFrame() {
        setTitle("Checkout Order");
        setLayout(new BorderLayout());
        ordersTableModel = new DefaultTableModel();
        ordersTable = new JTable(ordersTableModel);
        JPanel buttonPanel = getButtonPanel();
        add(new JScrollPane(ordersTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(totalPriceLabel = new JLabel());
        buttonPanel.add(checkoutButton = new JButton("Confirm Payment"));
        return buttonPanel;
    }

    public void addCheckoutButtonActionListener(ActionListener listener) {
        checkoutButton.addActionListener(listener);
    }

    public void updateOrderDetailsTable(String[] headers, String[][] data) {
        ordersTableModel.setDataVector(data, headers);
    }

    public void updateOrderTotalPriceLabel(BigDecimal totalPrice) {
        totalPriceLabel.setText("Total Price: " + totalPrice.toPlainString());
    }
}
