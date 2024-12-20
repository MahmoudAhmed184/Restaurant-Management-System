package org.software.controller.ordermanagement;

import org.software.model.order.Order;
import org.software.model.order.OrderManager;
import org.software.model.repositories.OrderRepository;
import org.software.utils.OrdersMapper;
import org.software.view.ordermanagement.OrderManagementPanel;

import java.util.List;

public class OrderManagementController {
    private final OrderManagementPanel orderManagementPanel;
    private final OrderRepository orderRepository;

    public OrderManagementController() {
        this.orderManagementPanel = OrderManagementPanel.getInstance();
        this.orderRepository = new OrderRepository();
    }

    public void initialize() {
        registerActionListener();
        loadTableData();
    }

    private void registerActionListener() {
        orderManagementPanel.addAddOrderButtonActionListener(e -> openAddNewOrderFrame());
        orderManagementPanel.addViewOrderButtonActionListener(e -> viewSelectedOrder());
    }

    private void loadTableData() {
        String[] columnNames = {"Order ID", "Type", "Table Number"};
        List<Order> orders = orderRepository.getAll();
        String[][] data = OrdersMapper.mapOrderData(orders);
        orderManagementPanel.updateOrdersTable(columnNames, data);
    }

    private void openAddNewOrderFrame() {
        NewOrderController newOrderController = new NewOrderController(orderManagementPanel.createNewOrderFrame());
        newOrderController.initialize();
    }

    private void viewSelectedOrder() {
        int selectedRow = orderManagementPanel.getSelectedRow();
        if (selectedRow == -1) {
            orderManagementPanel.displayMessageDialog("Please select an order to view.");
            return;
        }
        // @todo view Selected Row Logic

    }
}
