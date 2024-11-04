package com.bits.api_bp.food_del_sys_g12.repository;

import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByUserId(String userId); // Retrieve orders by user ID
}
