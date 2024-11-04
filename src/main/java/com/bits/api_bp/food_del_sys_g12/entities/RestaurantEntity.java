package com.bits.api_bp.food_del_sys_g12.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurants")
public class RestaurantEntity {

    @Id
    private String id;
    private String name;
    private String address;
    private String cuisine;
    private double rating;

}
