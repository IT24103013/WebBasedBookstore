package com.ku_26.bookstore.repository;

import com.ku_26.bookstore.model.Order;
import com.ku_26.bookstore.model.OrderStatus;
import com.ku_26.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByAssignedDeliveryStaff(User deliveryStaff);

    List<Order> findAllByAssignedDeliveryStaffAndOrderStatus(User deliveryStaff, OrderStatus status);
}

