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
        long activeDeliveries = orderRepository.countByStatus("In Progress");
        activity.put("activeDeliveries", activeDeliveries);
        return activity;
    }

    public Map<String, Object> getOrderStatusOverview() {
        Map<String, Object> overview = new HashMap<>();
        long pendingOrders = orderRepository.countByStatus("Pending");
        long completedOrders = orderRepository.countByStatus("Completed");
        overview.put("pendingOrders", pendingOrders);
        overview.put("completedOrders", completedOrders);
        return overview;
    }
}
