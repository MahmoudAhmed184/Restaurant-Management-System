package org.software.view.tablemanagement;

import javax.swing.*;
import java.awt.*;

public class TableManagementPanel extends JPanel {
    private static TableManagementPanel instance;

    private TableManagementPanel() {
        setLayout(new BorderLayout());
    }

    public static TableManagementPanel getInstance() {
        if (instance == null) {
            instance = new TableManagementPanel();
        }
        return instance;
    }
}
