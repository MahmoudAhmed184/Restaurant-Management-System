package org.software.view;

import org.software.view.menumanagement.MenuManagementPanel;
import org.software.view.ordermanagement.OrderManagementPanel;
import org.software.view.reservations.ReservationsManagementPanel;
import org.software.view.tablemanagement.TableManagementPanel;

import javax.swing.*;

public class MainDashboard extends JFrame {
    private static MainDashboard instance;
    private final JTabbedPane tabbedPane;

    private MainDashboard() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Order Management", OrderManagementPanel.getInstance());
        tabbedPane.addTab("Menu Management", MenuManagementPanel.getInstance());
        tabbedPane.addTab("Reservations Management", ReservationsManagementPanel.getInstance());
        tabbedPane.addTab("Table Management", TableManagementPanel.getInstance());
        this.add(tabbedPane);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static MainDashboard getInstance() {
        if (instance == null) {
            instance = new MainDashboard();
        }
        return instance;
    }

    public void switchToOrderManagementPanel() {
        tabbedPane.setSelectedIndex(0);
    }

    public void switchToMenuManagementPanel() {
        tabbedPane.setSelectedIndex(1);
    }

    public void switchToReservationManagementPanel() {
        tabbedPane.setSelectedIndex(2);
    }

    public void switchToTableManagementPanel() {
        tabbedPane.setSelectedIndex(3);
    }

    public void switchToPaymentPanel() {
        tabbedPane.setSelectedIndex(4);
    }
}
