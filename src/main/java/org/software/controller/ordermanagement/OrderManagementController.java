package org.software.controller.ordermanagement;

import org.software.model.order.Order;
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
        orderManagementPanel.addCheckoutOrderButtonActionListener(e -> checkoutOrder());
        orderManagementPanel.addCancelOrderButtonActionListener(e -> cancelOrder());

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

    private void checkoutOrder() {
        int selectedRow = orderManagementPanel.getSelectedRow();
        if (selectedRow == -1) {
            orderManagementPanel.displayMessageDialog("Please select an order to view.");
            return;
        }
        int orderId = Integer.parseInt(orderManagementPanel.getOrderTableValueAt(selectedRow, 0));
        Order order = orderRepository.getById(orderId);
        PaymentController paymentController = new PaymentController(order);
        paymentController.initialize();
    }

    private void cancelOrder() {
        int selectedRow = orderManagementPanel.getSelectedRow();
        if (selectedRow == -1) {
            orderManagementPanel.displayMessageDialog("Please select an order to view.");
            return;
        }
        int orderId = Integer.parseInt(orderManagementPanel.getOrderTableValueAt(selectedRow, 0));
        Order order = orderRepository.getById(orderId);
        orderRepository.delete(order);
        orderManagementPanel.removeRowFromOrdersTable(selectedRow);
        orderManagementPanel.displayMessageDialog("Order canceled.");
    }
}
