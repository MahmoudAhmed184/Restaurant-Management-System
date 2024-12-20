package org.software.model;

import org.software.model.order.Order;
import org.software.model.order.OrderItem;
import java.math.BigDecimal;

public class PaymentManager {
    private static PaymentManager instance;

    private PaymentManager() {
    }

    public static PaymentManager getInstance() {
        if (instance == null) {
            instance = new PaymentManager();
        }
        return instance;
    }

    public void pay() {
    }

    public BigDecimal getTotalPrice(Order order) {
        return order.items().stream()
            .map(PaymentManager::getItemTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal getItemTotal(OrderItem item) {
        return item.getItem().price().multiply(BigDecimal.valueOf(item.getQuantity()));
    }
}