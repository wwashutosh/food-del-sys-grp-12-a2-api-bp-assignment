package com.bits.api_bp.food_del_sys_g12.services;

import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Retrieve order by ID
    public OrderEntity getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Retrieve past orders for a user
    public List<OrderEntity> getPastOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
