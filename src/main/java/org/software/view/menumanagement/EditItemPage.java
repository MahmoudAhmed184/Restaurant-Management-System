package org.software.view.menumanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditItemPage extends JFrame {
    private final JTextField nameField;
    private final JTextField categoryField;
    private final JTextField priceField;
    private final JButton saveButton;
    private final JButton cancelButton;

    public EditItemPage(String itemName, String category, double price) {
        setTitle("Edit Item");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(itemName);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Category:"), gbc);

        gbc.gridx = 1;
        categoryField = new JTextField(category);
        add(categoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Price:"), gbc);

        gbc.gridx = 1;
        priceField = new JTextField(String.valueOf(price));
        add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);
    }

    public void setSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void setCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    public String getUpdatedName() {
        return nameField.getText().trim();
    }

    public String getUpdatedCategory() {
        return categoryField.getText().trim();
    }

    public double getUpdatedPrice() throws NumberFormatException {
        return Double.parseDouble(priceField.getText().trim());
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void close() {
        dispose();
    }
}
