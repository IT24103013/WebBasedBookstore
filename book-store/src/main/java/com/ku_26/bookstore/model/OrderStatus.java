package com.ku_26.bookstore.model;

/**
 * Represents the various states an order can be in.
 * Using an Enum makes the code cleaner and less error-prone
 * than using plain strings.
 */
public enum OrderStatus {
    CONFIRMED,
    OUT_FOR_DELIVERY,
    DELIVERED,
    FAILED
}