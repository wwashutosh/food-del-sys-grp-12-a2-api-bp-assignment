package com.bits.api_bp.food_del_sys_g12.repository;

import com.bits.api_bp.food_del_sys_g12.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItemEntity, String> {
    List<CartItemEntity> findByUserId(String userId);
    CartItemEntity findByUserIdAndMenuItemId(String userId, String menuItemId);
}
