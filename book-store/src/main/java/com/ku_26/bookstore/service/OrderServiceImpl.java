package com.ku_26.bookstore.service;

import com.ku_26.bookstore.model.Order;
import com.ku_26.bookstore.model.OrderStatus;
import com.ku_26.bookstore.model.User;
import com.ku_26.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrdersForDeliveryStaff(User deliveryStaff) {
        // Here we call the custom repository method.
        return orderRepository.findAllByAssignedDeliveryStaff(deliveryStaff);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    @Transactional // Ensures the operation is atomic. If it fails, the transaction is rolled back.
    public void updateOrderStatus(Long orderId, OrderStatus newStatus, String deliveryRemarks) {
        // Find the order in the database. If not found, throw an exception.
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        order.setOrderStatus(newStatus);
        if (deliveryRemarks != null && !deliveryRemarks.isEmpty()) {
            order.setDeliveryRemarks(deliveryRemarks);
        }

        orderRepository.save(order);
        // In a real application, this is where you would trigger a notification
        // to the customer (e.g., via email or SMS).
    }
}
