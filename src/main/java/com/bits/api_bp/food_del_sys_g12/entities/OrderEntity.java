package com.bits.api_bp.food_del_sys_g12.entities;

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
    private String id; // Unique ID for the order
    private String userId; // ID of the user who placed the order
    private String restaurantId; // ID of the restaurant
    private LocalDateTime orderDate; // Date and time when the order was placed
    private String status; // Status of the order (e.g., "DELIVERED", "PENDING", "CANCELLED")
}
