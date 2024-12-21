package org.software.view.menumanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuManagementAddItemFrame {
    private final JFrame frame;
    private final JTextField itemNameField;
    private final JTextField categoryField;
    private final JTextField priceField;
    private final JButton submitButton;

    public MenuManagementAddItemFrame() {
        frame = new JFrame("Add Item Form");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        itemNameField = new JTextField();
        categoryField = new JTextField();
        priceField = new JTextField();
        submitButton = new JButton("Submit");

        frame.add(new JLabel("Item Name:"));
        frame.add(itemNameField);
        frame.add(new JLabel("Category:"));
        frame.add(categoryField);
        frame.add(new JLabel("Price:"));
        frame.add(priceField);
        frame.add(new JLabel()); 
        frame.add(submitButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void setSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public String getItemName() {
        return itemNameField.getText().trim();
    }

    public String getCategory() {
        return categoryField.getText().trim();
    }

    public String getPrice() {
        return priceField.getText().trim();
    }
}
