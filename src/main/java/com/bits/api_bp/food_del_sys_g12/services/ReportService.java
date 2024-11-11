package com.bits.api_bp.food_del_sys_g12.services;

import com.bits.api_bp.food_del_sys_g12.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private OrderRepository orderRepository;

    public Map<String, Object> generatePopularRestaurantsReport() {
        Map<String, Object> report = new HashMap<>();
        List<Object[]> popularRestaurants = orderRepository.findPopularRestaurants();
        report.put("popularRestaurants", popularRestaurants);
        return report;
    }

    public Map<String, Object> generateAverageDeliveryTimeReport() {
        Map<String, Object> report = new HashMap<>();
        double avgDeliveryTime = orderRepository.calculateAverageDeliveryTime();
        report.put("averageDeliveryTime", avgDeliveryTime);
        return report;
    }

    public Map<String, Object> generateOrderTrendsReport() {
        Map<String, Object> report = new HashMap<>();
        List<Object[]> orderTrends = orderRepository.findOrderTrends();
        report.put("orderTrends", orderTrends);
        return report;
    }
}
