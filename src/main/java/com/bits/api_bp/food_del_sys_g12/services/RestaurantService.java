package com.bits.api_bp.food_del_sys_g12.services;

import com.bits.api_bp.food_del_sys_g12.entities.MenuEntity;
import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.entities.RestaurantEntity;
import com.bits.api_bp.food_del_sys_g12.repository.MenuRepository;
import com.bits.api_bp.food_del_sys_g12.repository.OrderRepository;
import com.bits.api_bp.food_del_sys_g12.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Add a new menu item
    public String addMenuItem(MenuEntity menuItem) {
        menuRepository.save(menuItem);
        return "Menu item added successfully";
    }

    // Update an existing menu item
    public String updateMenuItem(String id, MenuEntity updatedMenuItem) {
        Optional<MenuEntity> existingMenuItem = menuRepository.findById(id);
        if (existingMenuItem.isPresent()) {
            MenuEntity menuItem = existingMenuItem.get();
            menuItem.setName(updatedMenuItem.getName());
            menuItem.setDescription(updatedMenuItem.getDescription());
            menuItem.setPrice(updatedMenuItem.getPrice());
            menuItem.setAvailability(updatedMenuItem.getAvailability());
            menuRepository.save(menuItem);
            return "Menu item updated successfully";
        }
        return "Menu item not found";
    }

    // Delete a menu item
    public String deleteMenuItem(String id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return "Menu item deleted successfully";
        }
        return "Menu item not found";
    }

    // View incoming orders for a restaurant
    public List<OrderEntity> getIncomingOrders(String restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId);
    }

    // Update the status of an order
    public String updateOrderStatus(String orderId, String status) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            OrderEntity existingOrder = order.get();
            existingOrder.setStatus(status);
            orderRepository.save(existingOrder);
            return "Order status updated successfully";
        }
        return "Order not found";
    }

    // Update restaurant details
    public String updateRestaurantDetails(String restaurantId, RestaurantEntity updatedRestaurantDetails) {
        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            RestaurantEntity existingRestaurant = restaurant.get();
            existingRestaurant.setName(updatedRestaurantDetails.getName());
            existingRestaurant.setAddress(updatedRestaurantDetails.getAddress());
            existingRestaurant.setCuisine(updatedRestaurantDetails.getCuisine());
            existingRestaurant.setRating(updatedRestaurantDetails.getRating());
            restaurantRepository.save(existingRestaurant);
            return "Restaurant details updated successfully";
        }
        return "Restaurant not found";
    }
}
