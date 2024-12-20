package org.software.utils;

import org.software.model.order.Order;

import java.util.List;

public class OrdersMapper {
    private OrdersMapper() {
    }

    public static String[][] mapOrderData(List<Order> orders) {
        return orders.stream()
            .map(OrdersMapper::mapOrderData)
            .toArray(String[][]::new);
    }

    public static String[] mapOrderData(Order order) {
        return new String[]{
            String.valueOf(order.orderId()),
            String.valueOf(order.type()),
            String.valueOf(order.tableNumber() == -1 ? "" : order.tableNumber()),
        };
    }
}
