package com.bits.api_bp.food_del_sys_g12.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id; // Unique ID for the order
    @Column(name = "USERID", nullable = false)
    private String userId;
    @Column(name = "restaurant_id", nullable = false)
    private String restaurantId;// ID of the restaurant
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate; // Date and time when the order was placed
    @Column(name = "delivery_personnel_id", nullable = true)
    private String deliveryPersonnelId;
    @Column(name = "delivery_date", nullable = false)
    private LocalDateTime deliveryDate;
    @Column(name = "status", nullable = false)
    private String status; // Status of the order (e.g., "DELIVERED", "PENDING", "CANCELLED")
}
