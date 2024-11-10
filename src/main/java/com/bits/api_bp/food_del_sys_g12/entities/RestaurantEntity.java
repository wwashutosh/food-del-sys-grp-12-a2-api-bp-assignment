package com.bits.api_bp.food_del_sys_g12.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurants")
public class RestaurantEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "cuisine", nullable = false)
    private String cuisine;
    @Column(name = "rating", nullable = false)
    private double rating;

}
