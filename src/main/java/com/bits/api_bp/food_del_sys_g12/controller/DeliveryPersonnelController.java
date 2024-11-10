package com.bits.api_bp.food_del_sys_g12.controller;

import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.services.DeliveryPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/food_del_sys_g12/api/delivery")
public class DeliveryPersonnelController {

    @Autowired
    private DeliveryPersonnelService deliveryPersonnelService;

    // Endpoint to view assigned orders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getAssignedOrders(@RequestParam("deliveryPersonnelId") String deliveryPersonnelId) {
        List<OrderEntity> assignedOrders = deliveryPersonnelService.getAssignedOrders(deliveryPersonnelId);
        return ResponseEntity.ok(assignedOrders);
    }

    // Endpoint to update order status
    @PutMapping("/order/{id}/status")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable("id") String orderId,
            @RequestBody Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("status");
        String responseMessage = deliveryPersonnelService.updateOrderStatus(orderId, newStatus);
        return ResponseEntity.ok(responseMessage);
    }

    // Endpoint to update delivery personnel availability
    @PostMapping("/{id}/availability")
    public ResponseEntity<String> updateAvailability(@PathVariable String id, @RequestParam Boolean availability) {
        String responseMessage = deliveryPersonnelService.setAvailability(id, availability);
        return ResponseEntity.ok(responseMessage);
    }
}
