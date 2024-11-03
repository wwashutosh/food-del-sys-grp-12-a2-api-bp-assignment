package com.bits.api_bp.food_del_sys_g12.security.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Role {

  CUSTOMER,            // Role for customers
  RESTAURANT_OWNER,    // Role for restaurant owners
  DELIVERY_PERSONNEL,   // Role for delivery personnel
  ADMIN;               // Role for administrators

  public List<SimpleGrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.name()));
  }
}
