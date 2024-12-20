package org.software.view.reservations;

import javax.swing.*;
import java.awt.*;

public class ReservationsManagementPanel extends JPanel {
    private static ReservationsManagementPanel instance;
    private ReservationsManagementPanel() {
        setLayout(new BorderLayout());
    }
    public static ReservationsManagementPanel getInstance() {
        if (instance == null) {
            instance = new ReservationsManagementPanel();
        }
        return instance;
    }
}
