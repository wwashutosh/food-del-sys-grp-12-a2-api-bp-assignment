package com.bits.api_bp.food_del_sys_g12.controller;

import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.entities.Userdetail;
import com.bits.api_bp.food_del_sys_g12.services.AdminService;
import com.bits.api_bp.food_del_sys_g12.services.OrderService;
import com.bits.api_bp.food_del_sys_g12.services.ReportService;
import com.bits.api_bp.food_del_sys_g12.services.UserManagingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @Mock
    private UserManagingService userService;

    @Mock
    private OrderService orderService;

    @Mock
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
    }

    @Test
    void testUpdateUser() {
        Userdetail updatedUser = new Userdetail();
        updatedUser.setUserid("1");

        ResponseEntity<String> response = adminController.updateUser("1", updatedUser);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User updated successfully", response.getBody());

        verify(userService, times(1)).updateUser("1", updatedUser);
    }

    @Test
    void testDeactivateUser() {
        ResponseEntity<String> response = adminController.deactivateUser("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User deactivated successfully", response.getBody());

        verify(userService, times(1)).deactivateUser("1");
    }

    @Test
    void testGetAllOrders() {
        OrderEntity order1 = new OrderEntity();
        order1.setId("1");
        OrderEntity order2 = new OrderEntity();
        order2.setId("2");

        when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        ResponseEntity<List<OrderEntity>> response = adminController.getAllOrders();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void testUpdateOrderStatus() {
        ResponseEntity<String> response = adminController.updateOrderStatus("1", "Delivered");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order status updated successfully", response.getBody());

        verify(orderService, times(1)).updateOrderStatus("1", "Delivered");
    }

    @Test
    void testCancelOrder() {
        ResponseEntity<String> response = adminController.cancelOrder("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order cancelled successfully", response.getBody());

        verify(orderService, times(1)).cancelOrder("1");
    }

    @Test
    void testGetPopularRestaurantsReport() {
        Map<String, Object> report = Map.of("restaurant1", 50, "restaurant2", 75);
        when(reportService.generatePopularRestaurantsReport()).thenReturn(report);

        ResponseEntity<Map<String, Object>> response = adminController.getPopularRestaurantsReport();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(report, response.getBody());

        verify(reportService, times(1)).generatePopularRestaurantsReport();
    }

    @Test
    void testGetAverageDeliveryTimeReport() {
        Map<String, Object> report = Map.of("averageDeliveryTime", 30);
        when(reportService.generateAverageDeliveryTimeReport()).thenReturn(report);

        ResponseEntity<Map<String, Object>> response = adminController.getAverageDeliveryTimeReport();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(report, response.getBody());

        verify(reportService, times(1)).generateAverageDeliveryTimeReport();
    }

    @Test
    void testGetOrderTrendsReport() {
        Map<String, Object> report = Map.of("trends", "rising");
        when(reportService.generateOrderTrendsReport()).thenReturn(report);

        ResponseEntity<Map<String, Object>> response = adminController.getOrderTrendsReport();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(report, response.getBody());

        verify(reportService, times(1)).generateOrderTrendsReport();
    }

    @Test
    void testGetActiveUsersCount() {
        Map<String, Object> activity = Map.of("activeUsers", 120);
        when(adminService.getActiveUsersCount()).thenReturn(activity);

        ResponseEntity<Map<String, Object>> response = adminController.getActiveUsersCount();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(activity, response.getBody());

        verify(adminService, times(1)).getActiveUsersCount();
    }

    @Test
    void testGetDeliveryActivityStatus() {
        Map<String, Object> activity = Map.of("activeDeliveries", 50);
        when(adminService.getDeliveryActivityStatus()).thenReturn(activity);

        ResponseEntity<Map<String, Object>> response = adminController.getDeliveryActivityStatus();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(activity, response.getBody());

        verify(adminService, times(1)).getDeliveryActivityStatus();
    }

    @Test
    void testGetOrderStatusOverview() {
        Map<String, Object> activity = Map.of("ordersPending", 30);
        when(adminService.getOrderStatusOverview()).thenReturn(activity);

        ResponseEntity<Map<String, Object>> response = adminController.getOrderStatusOverview();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(activity, response.getBody());

        verify(adminService, times(1)).getOrderStatusOverview();
    }
}
