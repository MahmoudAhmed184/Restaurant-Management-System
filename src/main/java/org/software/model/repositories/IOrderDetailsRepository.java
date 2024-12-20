package org.software.model.repositories;

import org.software.model.order.Order;
import org.software.model.order.OrderItem;

import java.util.List;

public interface IOrderDetailsRepository extends Repository<OrderItem, Integer> {
    List<OrderItem> getAll();

    OrderItem getById(Integer id);

    OrderItem create(OrderItem orderItem);

    OrderItem update(OrderItem orderItem);

    OrderItem delete(OrderItem orderItem);
}
