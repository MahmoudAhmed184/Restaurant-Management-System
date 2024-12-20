package org.software.controller;

import org.software.controller.ordermanagement.OrderManagementController;
import org.software.view.MainDashboard;

public class MainController {
    private final MainDashboard mainDashboard;
    private final OrderManagementController orderManagementController;

    public MainController() {
        this.mainDashboard = MainDashboard.getInstance();
        this.orderManagementController = new OrderManagementController();
    }

    public void initialize() {
        configureMainDashBoard();
        orderManagementController.initialize();
    }

    private void configureMainDashBoard() {
        mainDashboard.setVisible(true);
        mainDashboard.setLocationRelativeTo(null);
        mainDashboard.setDefaultCloseOperation(MainDashboard.EXIT_ON_CLOSE);
    }
}
