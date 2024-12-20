package org.software.model.order;

import java.util.List;

public record Order(int orderId, String type, int tableNumber, List<OrderItem> items) {

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }
}
