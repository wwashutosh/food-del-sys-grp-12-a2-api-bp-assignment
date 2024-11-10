package com.bits.api_bp.food_del_sys_g12.controller;

import com.bits.api_bp.food_del_sys_g12.entities.CartItemEntity;
import com.bits.api_bp.food_del_sys_g12.entities.MenuEntity;
import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import com.bits.api_bp.food_del_sys_g12.entities.RestaurantEntity;
import com.bits.api_bp.food_del_sys_g12.model.FilteredDataForSuggestion;
import com.bits.api_bp.food_del_sys_g12.repository.RestaurantRepository;
import com.bits.api_bp.food_del_sys_g12.services.CustomerService;
import com.bits.api_bp.food_del_sys_g12.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetAllRestaurants() throws Exception {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setId("1");
        restaurant.setName("Test Restaurant");
        List<RestaurantEntity> restaurants = Arrays.asList(restaurant);

        when(restaurantRepository.findAll()).thenReturn(restaurants);

        mockMvc.perform(get("/food_del_sys_g12/api/customer/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Test Restaurant"));

        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void testGetRestaurantMenu() throws Exception {
        String restaurantId = "1";
        MenuEntity menuItem = new MenuEntity();
        menuItem.setId("100");
        menuItem.setName("Pizza");
        List<MenuEntity> menu = Arrays.asList(menuItem);

        when(customerService.getRestaurantMenu(restaurantId)).thenReturn(menu);

        mockMvc.perform(get("/food_del_sys_g12/api/customer/restaurants/{id}/menu", restaurantId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("100"))
                .andExpect(jsonPath("$[0].name").value("Pizza"));

        verify(customerService, times(1)).getRestaurantMenu(restaurantId);
    }

    @Test
    public void testFilterRestaurantsOrItems() throws Exception {
        String keyword = "Pizza";
        FilteredDataForSuggestion suggestion = new FilteredDataForSuggestion("", "", "");
        suggestion.setName("Pizza Restaurant");
        List<FilteredDataForSuggestion> filteredResults = Arrays.asList(suggestion);

        when(customerService.filterRestaurantsOrItems(keyword)).thenReturn(filteredResults);

        mockMvc.perform(get("/food_del_sys_g12/api/customer/filter").param("keyword", keyword))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza Restaurant"));

        verify(customerService, times(1)).filterRestaurantsOrItems(keyword);
    }

    @Test
    public void testGetCartContents() throws Exception {
        String userId = "user123";
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setId("cart1");
        List<CartItemEntity> cartItems = Arrays.asList(cartItem);

        when(customerService.viewCart(userId)).thenReturn(cartItems);

        mockMvc.perform(get("/food_del_sys_g12/api/customer/cart").param("userId", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("cart1"));

        verify(customerService, times(1)).viewCart(userId);
    }

    @Test
    public void testAddItemToCart() throws Exception {
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setId("cart1");

        when(customerService.addToCart(cartItem)).thenReturn("Item added successfully");

        mockMvc.perform(post("/food_del_sys_g12/api/customer/cart")
                        .contentType("application/json")
                        .content("{\"id\":\"cart1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Item added successfully"));

        verify(customerService, times(1)).addToCart(cartItem);
    }

    @Test
    public void testPlaceOrder() throws Exception {
        OrderEntity order = new OrderEntity();
        order.setId("order1");

        when(orderService.placeOrder(order)).thenReturn(order);

        mockMvc.perform(post("/food_del_sys_g12/api/customer/order")
                        .contentType("application/json")
                        .content("{\"id\":\"order1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("order1"));

        verify(orderService, times(1)).placeOrder(order);
    }

    @Test
    public void testTrackOrder() throws Exception {
        String orderId = "order1";
        OrderEntity order = new OrderEntity();
        order.setId(orderId);

        when(orderService.getOrderById(orderId)).thenReturn(order);

        mockMvc.perform(get("/food_del_sys_g12/api/customer/order/{id}/track", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId));

        verify(orderService, times(1)).getOrderById(orderId);
    }

    @Test
    public void testGetPastOrders() throws Exception {
        String userId = "user123";
        OrderEntity order = new OrderEntity();
        order.setId("order1");
        List<OrderEntity> pastOrders = Arrays.asList(order);

        when(orderService.getPastOrders(userId)).thenReturn(pastOrders);

        mockMvc.perform(get("/food_del_sys_g12/api/customer/orders").param("userId", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("order1"));

        verify(orderService, times(1)).getPastOrders(userId);
    }
}
