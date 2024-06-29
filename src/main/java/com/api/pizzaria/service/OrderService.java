package com.api.pizzaria.service;

import com.api.pizzaria.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void placeOrder(Order order);
}
