package com.bits.api_bp.food_del_sys_g12.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "delivery_personnel")
public class DeliveryPersonnelEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "availability", nullable = false)
    private Boolean availability; // Indicates whether the delivery personnel is available for delivery requests
}
