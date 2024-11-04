package com.bits.api_bp.food_del_sys_g12.services;

import com.bits.api_bp.food_del_sys_g12.entities.CartItemEntity;
import com.bits.api_bp.food_del_sys_g12.entities.MenuEntity;
import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.entities.RestaurantEntity;
import com.bits.api_bp.food_del_sys_g12.model.FilteredDataForSuggestion;
import com.bits.api_bp.food_del_sys_g12.repository.CartRepository;
import com.bits.api_bp.food_del_sys_g12.repository.MenuRepository;
import com.bits.api_bp.food_del_sys_g12.repository.OrderRepository;
import com.bits.api_bp.food_del_sys_g12.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Retrieve all available restaurants
    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Retrieve menu for a specific restaurant
    public List<MenuEntity> getRestaurantMenu(String restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }

    // Filter restaurants or items based on a keyword
    public List<FilteredDataForSuggestion> filterRestaurantsOrItems(String keyword) {
        List<FilteredDataForSuggestion> filteredResults = new ArrayList<>();

        // Filter restaurants
        List<RestaurantEntity> restaurants = restaurantRepository.findByNameContainingIgnoreCase(keyword);
        filteredResults.addAll(restaurants.stream()
                .map(restaurant -> new FilteredDataForSuggestion(restaurant.getId(), restaurant.getName(), "Restaurant"))
                .collect(Collectors.toList()));

        // Filter menu items
        List<MenuEntity> menuItems = menuRepository.findByNameContainingIgnoreCase(keyword);
        filteredResults.addAll(menuItems.stream()
                .map(item -> new FilteredDataForSuggestion(item.getId(), item.getName(), "Menu Item"))
                .collect(Collectors.toList()));

        return filteredResults;
    }

    // View contents of the cart for a user
    public List<CartItemEntity> viewCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    // Add an item to the cart
    public String addToCart(CartItemEntity itemDetails) {
        // Logic to check if the item already exists in the cart
        CartItemEntity existingItem = cartRepository.findByUserIdAndMenuItemId(itemDetails.getUserId(), itemDetails.getMenuItemId());
        if (existingItem != null) {
            // Update the quantity if the item already exists
            existingItem.setQuantity(existingItem.getQuantity() + itemDetails.getQuantity());
            cartRepository.save(existingItem);
            return "Item quantity updated in the cart.";
        } else {
            // Save new item to the cart
            cartRepository.save(itemDetails);
            return "Item added to the cart.";
        }
    }

    // Place an order
    public OrderEntity placeOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    // Get past orders for a user
    public List<OrderEntity> getPastOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
