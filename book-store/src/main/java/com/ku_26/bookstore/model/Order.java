package com.ku_26.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerDetails;
    private String shippingAddress;

    // EnumType.STRING stores the enum name ("DELIVERED") in the database,
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String deliveryRemarks;

    // This creates the many-to-one relationship with the User entity.
    @ManyToOne
    @JoinColumn(name = "assigned_delivery_staff_id")
    private User assignedDeliveryStaff;


    public Long getId() {
        return id;
    }

    public String getCustomerDetails() {
        return customerDetails;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getDeliveryRemarks() {
        return deliveryRemarks;
    }

    public User getAssignedDeliveryStaff() {
        return assignedDeliveryStaff;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerDetails(String customerDetails) {
        this.customerDetails = customerDetails;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setDeliveryRemarks(String deliveryRemarks) {
        this.deliveryRemarks = deliveryRemarks;
    }

    public void setAssignedDeliveryStaff(User assignedDeliveryStaff) {
        this.assignedDeliveryStaff = assignedDeliveryStaff;
    }
}
