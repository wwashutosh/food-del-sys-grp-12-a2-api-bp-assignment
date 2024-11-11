package com.bits.api_bp.food_del_sys_g12.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "cart_items")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id; // Unique identifier for each cart item

    @Column(name = "USERID", nullable = false)
    private String userId;

    @Column(name = "menu_item_id", nullable = false)
    private String menuItemId; // ID of the menu item

    @Column(name = "quantity", nullable = false)
    private Integer quantity; // Quantity of the menu item

}
