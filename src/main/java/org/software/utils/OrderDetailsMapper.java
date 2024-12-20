package org.software.utils;

import org.software.model.order.Order;

public class OrderDetailsMapper {
    private OrderDetailsMapper() {
    }

    public static String[][] mapOrderData(Order order) {
        return order.items().stream()
            .map(i -> new String[]
                {
                    String.valueOf(i.getItem().id()),
                    String.valueOf(i.getItem().name()),
                    String.valueOf(i.getQuantity())

                }).toArray(String[][]::new);
    }
}
