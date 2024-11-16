package com.bits.api_bp.food_del_sys_g12.services;

import com.bits.api_bp.food_del_sys_g12.repository.OrderRepository;
import com.bits.api_bp.food_del_sys_g12.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AdminService {

    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Map<String, Object> getActiveUsersCount() {
        Map<String, Object> activity = new HashMap<>();
        long activeUsers = userRepository.countByActive(true);
        activity.put("activeUsers", activeUsers);
        return activity;
    }

    public Map<String, Object> getDeliveryActivityStatus() {
        Map<String, Object> activity = new HashMap<>();
        long activeDeliveries = orderRepository.countByStatus("PENDING");
        activity.put("activeDeliveries", activeDeliveries);
        long delivered = orderRepository.countByStatus("DELIVERED");
        activity.put("delivered", delivered);
        return activity;
    }

    public Map<String, Object> getOrderStatusOverview() {
        Map<String, Object> overview = new HashMap<>();
        long pendingOrders = orderRepository.countByStatus("PENDING");
        long completedOrders = orderRepository.countByStatus("DELIVERED");
        long cancelledOrders = orderRepository.countByStatus("CANCELLED");

        overview.put("pendingOrders", pendingOrders);
        overview.put("completedOrders", completedOrders);
        overview.put("cancelledOrders",cancelledOrders);
        return overview;
    }
}
