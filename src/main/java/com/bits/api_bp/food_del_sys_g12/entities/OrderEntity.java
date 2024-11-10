package com.bits.api_bp.food_del_sys_g12.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id; // Unique ID for the order
    @Column(name = "USERID", nullable = false)
    private String userid;
    @Column(name = "restaurant_id", nullable = false)
    private String restaurantId;// ID of the restaurant
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate; // Date and time when the order was placed
    @Column(name = "delivery_personnel_id", nullable = true)
    private String deliveryPersonnelId;
    @Column(name = "status", nullable = false)
    private String status; // Status of the order (e.g., "DELIVERED", "PENDING", "CANCELLED")
}
