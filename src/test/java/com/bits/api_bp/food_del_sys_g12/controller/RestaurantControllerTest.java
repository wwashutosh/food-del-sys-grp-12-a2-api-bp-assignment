package com.bits.api_bp.food_del_sys_g12.controller;

import com.bits.api_bp.food_del_sys_g12.entities.MenuEntity;
import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.entities.RestaurantEntity;
import com.bits.api_bp.food_del_sys_g12.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMenuItem() {
        MenuEntity menuItem = new MenuEntity();
        menuItem.setName("Burger");

        when(restaurantService.addMenuItem(any(MenuEntity.class))).thenReturn("Menu item added successfully");

        ResponseEntity<String> response = restaurantController.addMenuItem(menuItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Menu item added successfully", response.getBody());
    }

    @Test
    void testUpdateMenuItem() {
        MenuEntity menuItem = new MenuEntity();
        menuItem.setName("Updated Burger");

        when(restaurantService.updateMenuItem(anyString(), any(MenuEntity.class))).thenReturn("Menu item updated successfully");

        ResponseEntity<String> response = restaurantController.updateMenuItem("menu1", menuItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Menu item updated successfully", response.getBody());
    }

    @Test
    void testDeleteMenuItem() {
        when(restaurantService.deleteMenuItem(anyString())).thenReturn("Menu item deleted successfully");

        ResponseEntity<String> response = restaurantController.deleteMenuItem("menu1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Menu item deleted successfully", response.getBody());
    }

    @Test
    void testViewIncomingOrders() {
        OrderEntity order1 = new OrderEntity();
        order1.setId("order1");
        OrderEntity order2 = new OrderEntity();
        order2.setId("order2");

        List<OrderEntity> orders = Arrays.asList(order1, order2);
        when(restaurantService.getIncomingOrders(anyString())).thenReturn(orders);

        ResponseEntity<List<OrderEntity>> response = restaurantController.viewIncomingOrders("rest1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testUpdateOrderStatus() {
        when(restaurantService.updateOrderStatus(anyString(), anyString())).thenReturn("Order status updated successfully");

        ResponseEntity<String> response = restaurantController.updateOrderStatus("order1", "Delivered");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order status updated successfully", response.getBody());
    }

    @Test
    void testUpdateRestaurantDetails() {
        RestaurantEntity restaurantDetails = new RestaurantEntity();
        restaurantDetails.setName("Updated Restaurant");

        when(restaurantService.updateRestaurantDetails(anyString(), any(RestaurantEntity.class))).thenReturn("Restaurant details updated successfully");

        ResponseEntity<String> response = restaurantController.updateRestaurantDetails("rest1", restaurantDetails);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Restaurant details updated successfully", response.getBody());
    }
}
