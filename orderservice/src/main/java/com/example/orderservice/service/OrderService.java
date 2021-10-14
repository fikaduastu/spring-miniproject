package com.example.orderservice.service;

import com.example.orderservice.dto.ResponseOrderWithProductDTO;
import com.example.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);

    Order getOrderById(long orderId);

    List<Order> getAllOrders();
    ResponseOrderWithProductDTO getOrderWithProduct(long userId, long productId);

    List<Order> getOrderByUserId(long userId);
}
