package org.software.controller;

import org.software.controller.menu.MenuController;
import org.software.controller.ordermanagement.OrderManagementController;
import org.software.controller.ordermanagement.PaymentController;
import org.software.view.MainDashboard;

public class MainController {
    private final MainDashboard mainDashboard;
    private final OrderManagementController orderManagementController;
    private final MenuController menuController;

    public MainController() {
        this.mainDashboard = MainDashboard.getInstance();
        this.orderManagementController = new OrderManagementController();
        this.menuController = new MenuController();
    }

    public void initialize() {
        configureMainDashBoard();
        orderManagementController.initialize();
        menuController.initialize();
    }

    private void configureMainDashBoard() {
        mainDashboard.setVisible(true);
        mainDashboard.setLocationRelativeTo(null);
        mainDashboard.setDefaultCloseOperation(MainDashboard.EXIT_ON_CLOSE);
    }
}
