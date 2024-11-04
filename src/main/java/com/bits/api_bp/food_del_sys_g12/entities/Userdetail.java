package com.bits.api_bp.food_del_sys_g12.entities;

import com.bits.api_bp.food_del_sys_g12.security.entity.Role;
import com.bits.api_bp.food_del_sys_g12.security.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class Userdetail implements UserDetails {
    @Id
    @Column(name = "USERID", nullable = false)
    private String userid;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAILADDRESS", nullable = false)
    private String emailaddress;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "RESTAURANT_HOURS")
    private String restaurantHours;
    @Column(name = "DELIVERY_VEHICLE")
    private String deliveryVehicle;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailaddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}