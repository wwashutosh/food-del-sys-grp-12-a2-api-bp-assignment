package com.bits.api_bp.food_del_sys_g12.services;

import com.bits.api_bp.food_del_sys_g12.entities.DeliveryPersonnelEntity;
import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.repository.DeliveryPersonnelRepository;
import com.bits.api_bp.food_del_sys_g12.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPersonnelService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryPersonnelRepository deliveryPersonnelRepository;

    // Fetch all assigned orders for a delivery personnel
    public List<OrderEntity> getAssignedOrders(String deliveryPersonnelId) {
        return orderRepository.findByDeliveryPersonnelIdAndStatusNot(deliveryPersonnelId, "Delivered");
    }

    // Update the status of a specific order
    public String updateOrderStatus(String orderId, String newStatus) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            OrderEntity orderEntity = order.get();
            orderEntity.setStatus(newStatus);
            orderRepository.save(orderEntity);
            return "Order status updated to " + newStatus;
        }
        return "Order not found";
    }

    // Fetch completed delivery history for a delivery personnel
    public List<OrderEntity> getDeliveryHistory(String deliveryPersonnelId) {
        return orderRepository.findByDeliveryPersonnelIdAndStatus(deliveryPersonnelId, "Delivered");
    }

    // Set delivery personnel availability
    public String setAvailability(String id, Boolean availability) {
        Optional<DeliveryPersonnelEntity> personnel = deliveryPersonnelRepository.findById(id);
        if (personnel.isPresent()) {
            DeliveryPersonnelEntity deliveryPersonnel = personnel.get();
            deliveryPersonnel.setAvailability(availability);
            deliveryPersonnelRepository.save(deliveryPersonnel);
            return "Availability updated successfully";
        }
        return "Delivery personnel not found";
    }
}
