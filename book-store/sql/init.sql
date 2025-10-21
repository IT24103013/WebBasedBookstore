-- This table stores user information, including delivery staff.
-- The 'role' column is crucial for Spring Security.
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL, -- Always store hashed passwords!
                       full_name VARCHAR(100),
                       role VARCHAR(20) NOT NULL, -- e.g., 'ADMIN', 'MANAGER', 'DELIVERY_STAFF'
                       enabled BOOLEAN NOT NULL DEFAULT TRUE
);

-- order details.
CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        customer_details TEXT NOT NULL,
                        shipping_address TEXT NOT NULL,
                        order_status VARCHAR(50) NOT NULL, -- e.g., 'CONFIRMED', 'OUT_FOR_DELIVERY', 'DELIVERED', 'FAILED'
                        delivery_remarks VARCHAR(255), -- For notes like 'Customer not available'.
                        assigned_delivery_staff_id BIGINT,
                        FOREIGN KEY (assigned_delivery_staff_id) REFERENCES users(id)
);

-- The password 'password123' should be hashed in a real application.
-- used a placeholder for Spring Security's BCryptPasswordEncoder.
-- A securely hashed version of 'password123'
INSERT INTO users (username, password, full_name, role) VALUES
    ('delivery_user', '{bcrypt}$2a$10$3g5v.1Oq2.EaNlXJ5c.Jp.g7YVwxmJ5/V6v.dO0C2q4iP.2bH2hE2', 'John Doe', 'DELIVERY_STAFF');

-- And some sample orders assigned to this user.
INSERT INTO orders (customer_details, shipping_address, order_status, assigned_delivery_staff_id) VALUES
                                                                                                      ('Jane Smith, 555-1234', '123 Maple Street, Anytown', 'CONFIRMED', 1),
                                                                                                      ('Mark Johnson, 555-5678', '456 Oak Avenue, Otherville', 'CONFIRMED', 1);