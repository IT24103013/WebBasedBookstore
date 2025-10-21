package com.ku_26.bookstore.service;

import com.ku_26.bookstore.model.Order;
import com.ku_26.bookstore.model.OrderStatus;
import com.ku_26.bookstore.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getOrdersForDeliveryStaff(User deliveryStaff);
    Optional<Order> getOrderById(Long orderId);
    void updateOrderStatus(Long orderId, OrderStatus newStatus, String deliveryRemarks);
}