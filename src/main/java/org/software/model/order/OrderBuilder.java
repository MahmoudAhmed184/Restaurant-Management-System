package org.software.model.order;

import org.software.model.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    private int orderId = -1;
    private String type = "take away";
    private int tableNumber = -1;
    private List<OrderItem> items = new ArrayList<OrderItem>();

    public OrderBuilder() {

    }

    public OrderBuilder setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public OrderBuilder setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
        return this;
    }

    public OrderBuilder setItems(List<OrderItem> items) {
        this.items = items;
        return this;
    }

    public void addItem(MenuItem menuItem) {
        items.stream()
                .filter(orderItem -> orderItem.getItem().equals(menuItem))
                .findFirst()
                .ifPresentOrElse(orderItem -> orderItem.setQuantity(orderItem.getQuantity() + 1),
                        () -> items.add(new OrderItem(menuItem, 1)));
    }

    public void removeItem(int id) {
        items.removeIf(orderItem -> orderItem.getItem().id() == id);
    }

    public Order build() {
        return new Order(orderId, type, tableNumber, items);
    }

}
