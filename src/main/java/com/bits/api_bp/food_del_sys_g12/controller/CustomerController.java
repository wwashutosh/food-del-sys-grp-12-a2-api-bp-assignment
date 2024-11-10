package com.bits.api_bp.food_del_sys_g12.controller;

import com.bits.api_bp.food_del_sys_g12.entities.CartItemEntity;
import com.bits.api_bp.food_del_sys_g12.entities.MenuEntity;
import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.entities.RestaurantEntity;
import com.bits.api_bp.food_del_sys_g12.model.FilteredDataForSuggestion;
import com.bits.api_bp.food_del_sys_g12.repository.RestaurantRepository;
import com.bits.api_bp.food_del_sys_g12.services.CustomerService;
import com.bits.api_bp.food_del_sys_g12.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@SuppressWarnings("unused")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/food_del_sys_g12/api/customer")
public class CustomerController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;


    // Endpoint to view list of all available restaurants
    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantEntity>> getAllRestaurants() {
        List<RestaurantEntity> restaurants = restaurantRepository.findAll();
        return ResponseEntity.ok(restaurants);
    }

    // Endpoint to view menu of a specific restaurant
    @GetMapping("/restaurants/{id}/menu")
    public ResponseEntity<List<MenuEntity>> getRestaurantMenu(@PathVariable("id") String restaurantId) {
        List<MenuEntity> menu = customerService.getRestaurantMenu(restaurantId);
        return ResponseEntity.ok(menu);
    }

    // Endpoint to search for food items/restaurants based on a keyword
    @GetMapping("/filter")
    public ResponseEntity<List<FilteredDataForSuggestion>> filterRestaurantsOrItems(@RequestParam("keyword") String keyword) {
        List<FilteredDataForSuggestion> filteredResults = customerService.filterRestaurantsOrItems(keyword);
        return ResponseEntity.ok(filteredResults);
    }

    // Endpoint to view cart contents
    // View cart contents
    @GetMapping("/cart")
    public ResponseEntity<List<CartItemEntity>> getCartContents(@RequestParam("userId") String userId) {
        List<CartItemEntity> cartContents = customerService.viewCart(userId);
        return ResponseEntity.ok(cartContents);
    }

    // Add items to cart
    @PostMapping("/cart")
    public ResponseEntity<String> addItemToCart(@RequestBody CartItemEntity itemDetails) {
        String responseMessage = customerService.addToCart(itemDetails);
        return ResponseEntity.ok(responseMessage);
    }

    // Endpoint to place an order
    @PostMapping("/order")
    public ResponseEntity<OrderEntity> placeOrder(@RequestBody OrderEntity order) {
        OrderEntity placedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(placedOrder); // Returns the placed order with the generated ID and other details
    }

    // Endpoint to track an order by order ID
    @GetMapping("/order/{id}/track")
    public ResponseEntity<OrderEntity> trackOrder(@PathVariable("id") String orderId) {
        OrderEntity order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order); // Returns the order details including the status
    }

    // Endpoint to view list of past orders
    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getPastOrders(@RequestParam("userId") String userId) {
        List<OrderEntity> pastOrders = orderService.getPastOrders(userId);
        return ResponseEntity.ok(pastOrders); // Returns a list of past orders for the user
    }
}
