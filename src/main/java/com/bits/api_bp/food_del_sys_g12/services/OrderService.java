package com.bits.api_bp.food_del_sys_g12.services;

import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Retrieve order by ID
    public OrderEntity getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }
    // Place an order
    public OrderEntity placeOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    // Retrieve past orders for a user
    public List<OrderEntity> getPastOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
    public void updateOrderStatus(String orderId, String status) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        order.ifPresent(o -> {
            o.setStatus(status);
            orderRepository.save(o);
        });
    }
    public void cancelOrder(String orderId) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        order.ifPresent(o -> {
            o.setStatus("Cancelled");
            orderRepository.save(o);
        });
    }
}
