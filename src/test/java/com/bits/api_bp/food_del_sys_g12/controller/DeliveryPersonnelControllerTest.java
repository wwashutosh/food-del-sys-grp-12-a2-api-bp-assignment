package com.bits.api_bp.food_del_sys_g12.controller;

import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.services.DeliveryPersonnelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DeliveryPersonnelControllerTest {

    @Mock
    private DeliveryPersonnelService deliveryPersonnelService;

    @InjectMocks
    private DeliveryPersonnelController deliveryPersonnelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAssignedOrders() {
        OrderEntity order1 = new OrderEntity();
        order1.setId("order1");
        OrderEntity order2 = new OrderEntity();
        order2.setId("order2");

        List<OrderEntity> assignedOrders = Arrays.asList(order1, order2);
        when(deliveryPersonnelService.getAssignedOrders(anyString())).thenReturn(assignedOrders);

        ResponseEntity<List<OrderEntity>> response = deliveryPersonnelController.getAssignedOrders("deliveryPersonnel1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testUpdateOrderStatus() {
        when(deliveryPersonnelService.updateOrderStatus(anyString(), anyString())).thenReturn("Order status updated successfully");

        ResponseEntity<String> response = deliveryPersonnelController.updateOrderStatus("order1", Map.of("status", "Delivered"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order status updated successfully", response.getBody());
    }

    @Test
    void testUpdateAvailability() {
        when(deliveryPersonnelService.setAvailability(anyString(), anyBoolean())).thenReturn("Availability updated successfully");

        ResponseEntity<String> response = deliveryPersonnelController.updateAvailability("deliveryPersonnel1", true);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Availability updated successfully", response.getBody());
    }
}
