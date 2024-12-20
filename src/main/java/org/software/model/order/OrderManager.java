package org.software.model.order;

import org.software.model.menu.MenuItem;
import org.software.model.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private final OrderRepository orderRepository;
    private final List<Order> orders;
    private OrderBuilder orderBuilder;

    private OrderManager() {
        orders = new ArrayList<>();
        orderBuilder = new OrderBuilder();
        orderRepository = new OrderRepository();
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addItemToOrder(MenuItem menuItem) {
        orderBuilder.addItem(menuItem);
    }

    public void removeItemFromOrder(int id) {
        orderBuilder.removeItem(id);
    }

    public Order getCurrentOrder() {
        return orderBuilder.build();
    }

    public void clearOrder() {
        orderBuilder.setItems(new ArrayList<>());
    }

    public void createNewOrder() {
        orderBuilder = new OrderBuilder();
    }

    public Order saveOrder() {
        Order order = orderRepository.create(orderBuilder.build());
        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrderType(String selectedOrderType) {
        orderBuilder.setType(selectedOrderType);
    }

    public void setTableNumber(int tableNumber) {
        orderBuilder.setTableNumber(tableNumber);
    }
}
