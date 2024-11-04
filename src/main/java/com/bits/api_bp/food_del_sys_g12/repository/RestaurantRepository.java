package com.bits.api_bp.food_del_sys_g12.repository;

import com.bits.api_bp.food_del_sys_g12.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, String> {
    List<RestaurantEntity> findByNameContainingIgnoreCase(String keyword);
}
