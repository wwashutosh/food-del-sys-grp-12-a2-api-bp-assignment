package com.bits.api_bp.food_del_sys_g12.controller;

import com.bits.api_bp.food_del_sys_g12.entities.MenuEntity;
import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.entities.RestaurantEntity;
import com.bits.api_bp.food_del_sys_g12.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/food_del_sys_g12/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // Endpoint to manage menu items
    @PostMapping("/menu")
    public ResponseEntity<String> addMenuItem(@RequestBody MenuEntity menuItem) {
        String response = restaurantService.addMenuItem(menuItem);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<String> updateMenuItem(@PathVariable String id, @RequestBody MenuEntity menuItem) {
        String response = restaurantService.updateMenuItem(id, menuItem);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable String id) {
        String response = restaurantService.deleteMenuItem(id);
        return ResponseEntity.ok(response);
    }

    // Endpoint to view incoming orders for the restaurant
    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> viewIncomingOrders(@RequestParam String restaurantId) {
        List<OrderEntity> orders = restaurantService.getIncomingOrders(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String orderId, @RequestParam String status) {
        String response = restaurantService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(response);
    }

    // Endpoint to update restaurant details
    @PutMapping("/{restaurantId}")
    public ResponseEntity<String> updateRestaurantDetails(@PathVariable String restaurantId, @RequestBody RestaurantEntity restaurantDetails) {
        String response = restaurantService.updateRestaurantDetails(restaurantId, restaurantDetails);
        return ResponseEntity.ok(response);
    }
}
