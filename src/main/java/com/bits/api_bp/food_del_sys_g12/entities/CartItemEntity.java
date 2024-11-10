package com.bits.api_bp.food_del_sys_g12.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_items")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each cart item

    @Column(name = "USERID", nullable = false)
    private String userId;

    @Column(name = "menu_item_id", nullable = false)
    private String menuItemId; // ID of the menu item

    @Column(name = "quantity", nullable = false)
    private Integer quantity; // Quantity of the menu item

    // Constructors, getters, and setters can be generated by Lombok with the @Data annotation
}