package org.software.view.ordermanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OrderTypePanel extends JPanel {
    private final JComboBox<String> orderTypeComboBox;
    private final JTextField tableNumberField;
    private final JLabel tableNumberLabel;

    public OrderTypePanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createTitledBorder("Order Type"));
        orderTypeComboBox = new JComboBox<>(new String[]{"Takeaway", "Dine In"});
        tableNumberLabel = new JLabel("Table Number:");
        tableNumberField = new JTextField(10);
        tableNumberLabel.setVisible(false);
        tableNumberField.setVisible(false);
        orderTypeComboBox.addActionListener(this::handleOrderTypeChange);
        this.add(new JLabel("Order Type:"));
        this.add(orderTypeComboBox);
        this.add(tableNumberLabel);
        this.add(tableNumberField);
    }

    public String getSelectedOrderType() {
        return (String) orderTypeComboBox.getSelectedItem();
    }

    public String getTableNumber() {
        return tableNumberField.getText();
    }

    private void handleOrderTypeChange(ActionEvent e) {
        boolean isDineIn = "Dine In".equals(orderTypeComboBox.getSelectedItem());
        tableNumberLabel.setVisible(isDineIn);
        tableNumberField.setVisible(isDineIn);

        if (!isDineIn) {
            tableNumberField.setText("");
        }
    }
}
