package com.bits.api_bp.food_del_sys_g12.security.auth;


import com.bits.api_bp.food_del_sys_g12.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
    private String address;
    private String phone;
    private String restaurantHours;
    private String deliveryVehicle;
}
