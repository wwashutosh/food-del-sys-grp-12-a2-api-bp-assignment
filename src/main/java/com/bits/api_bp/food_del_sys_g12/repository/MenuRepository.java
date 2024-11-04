package com.bits.api_bp.food_del_sys_g12.repository;

import com.bits.api_bp.food_del_sys_g12.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, String> {
    List<MenuEntity> findByNameContainingIgnoreCase(String keyword);

    List<MenuEntity> findByRestaurantId(String restaurantId);

}
