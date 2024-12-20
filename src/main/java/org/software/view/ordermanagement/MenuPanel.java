package org.software.view.ordermanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MenuPanel extends JPanel {
    private final DefaultTableModel menuTableModel;
    private final JTable menuTable;
    public MenuPanel() {
        this.setLayout(new BorderLayout());
        menuTable = new JTable(menuTableModel = new DefaultTableModel());
        this.add(new JScrollPane(menuTable), BorderLayout.CENTER);
    }

    public String getMenuTableValueAt(int row, int column) {
        return menuTable.getValueAt(row, column).toString();
    }

    public int getSelectedMenuTableRow() {
        return menuTable.getSelectedRow();
    }

    public void updateMenuTable(String[] menuColumns, String[][] menuData) {
        menuTableModel.setDataVector(menuData, menuColumns);
    }


}
