package org.software.model.repositories;

import org.software.model.order.Order;

import java.util.List;

public interface IOrderRepository extends Repository <Order, Integer> {
    List<Order> getAll();

    Order getById(Integer id);

    Order create(Order order);

    Order update(Order order);

    Order delete(Order order);
}
