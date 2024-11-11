package com.bits.api_bp.food_del_sys_g12.controller;

import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.entities.Userdetail;
import com.bits.api_bp.food_del_sys_g12.services.AdminService;
import com.bits.api_bp.food_del_sys_g12.services.OrderService;
import com.bits.api_bp.food_del_sys_g12.services.ReportService;
import com.bits.api_bp.food_del_sys_g12.services.UserManagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/food_del_sys_g12/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserManagingService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReportService reportService;

    // Manage Users - create, update, deactivate user accounts
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody Userdetail user) {
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody Userdetail updatedUser) {
        userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok("User updated successfully");
    }

    @PutMapping("/users/{userId}/deactivate")
    public ResponseEntity<String> deactivateUser(@PathVariable String userId) {
        userService.deactivateUser(userId);
        return ResponseEntity.ok("User deactivated successfully");
    }

    // View and Manage Orders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Order status updated successfully");
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable String orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order cancelled successfully");
    }

    // Generate Reports
    @GetMapping("/reports/popular-restaurants")
    public ResponseEntity<Map<String, Object>> getPopularRestaurantsReport() {
        Map<String, Object> report = reportService.generatePopularRestaurantsReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/reports/delivery-time")
    public ResponseEntity<Map<String, Object>> getAverageDeliveryTimeReport() {
        Map<String, Object> report = reportService.generateAverageDeliveryTimeReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/reports/order-trends")
    public ResponseEntity<Map<String, Object>> getOrderTrendsReport() {
        Map<String, Object> report = reportService.generateOrderTrendsReport();
        return ResponseEntity.ok(report);
    }

    // Monitor Platform Activity
    @GetMapping("/activity/active-users")
    public ResponseEntity<Map<String, Object>> getActiveUsersCount() {
        Map<String, Object> activity = adminService.getActiveUsersCount();
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/activity/delivery-activity")
    public ResponseEntity<Map<String, Object>> getDeliveryActivityStatus() {
        Map<String, Object> activity = adminService.getDeliveryActivityStatus();
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/activity/order-status")
    public ResponseEntity<Map<String, Object>> getOrderStatusOverview() {
        Map<String, Object> activity = adminService.getOrderStatusOverview();
        return ResponseEntity.ok(activity);
    }
}
