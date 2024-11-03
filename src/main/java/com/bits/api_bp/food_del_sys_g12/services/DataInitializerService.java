package com.bits.api_bp.food_del_sys_g12.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class DataInitializerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UUIDService uuidService;

    @PostConstruct
    public void populateDummyData() {
        dropTables();
        createTables();
        insertDummyData();
    }

    private void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS cart_items;");
        jdbcTemplate.execute("DROP TABLE IF EXISTS orders;");
        jdbcTemplate.execute("DROP TABLE IF EXISTS menu;");
        jdbcTemplate.execute("DROP TABLE IF EXISTS restaurants;");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users;");
    }

    private void createTables() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (" +
                "id CHAR(6) PRIMARY KEY," +
                "name VARCHAR(255)," +
                "email VARCHAR(255) UNIQUE," +
                "password VARCHAR(255)," +
                "role VARCHAR(50)" +
                ");");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS restaurants (" +
                "id CHAR(6) PRIMARY KEY," +
                "name VARCHAR(255)," +
                "address VARCHAR(255)," +
                "cuisine VARCHAR(100)," +
                "rating DECIMAL(2, 1)" +
                ");");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS menu (" +
                "id CHAR(6) PRIMARY KEY," +
                "restaurant_id CHAR(6)," +
                "item_name VARCHAR(255)," +
                "description VARCHAR(255)," +
                "price DECIMAL(10, 2)," +
                "availability BOOLEAN" +
                ");");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS orders (" +
                "id CHAR(6) PRIMARY KEY," +
                "user_id CHAR(6)," +
                "restaurant_id CHAR(6)," +
                "order_date TIMESTAMP," +
                "status VARCHAR(50)" +
                ");");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS cart_items (" +
                "id CHAR(6) PRIMARY KEY," +
                "user_id CHAR(6)," +
                "menu_item_id CHAR(6)," +
                "quantity INT" +
                ");");
    }

    private void insertDummyData() {
        // Insert users
        insertUser("John Doe", "john@example.com", "hashed_password", "CUSTOMER");
        insertUser("Jane Smith", "jane@example.com", "hashed_password", "RESTAURANT");
        insertUser("Alice Johnson", "alice@example.com", "hashed_password", "DELIVERY");
        insertUser("Bob Brown", "bob@example.com", "hashed_password", "CUSTOMER");
        insertUser("Charlie Davis", "charlie@example.com", "hashed_password", "ADMIN");

        // Insert restaurants
        insertRestaurant("The Great Indian Bistro", "123 Curry Lane, New Delhi", "Indian", 4.5);
        insertRestaurant("Pizza Paradise", "456 Cheese St, Mumbai", "Italian", 4.2);
        insertRestaurant("Sushi World", "789 Fish Ave, Tokyo", "Japanese", 4.7);
        insertRestaurant("Taco Fiesta", "321 Spice Rd, Mexico City", "Mexican", 4.1);
        insertRestaurant("Burger Haven", "654 Meat St, Chicago", "American", 4.6);

        // Insert menu items
        insertMenuItem("ITEM01", "Butter Chicken", "Creamy chicken curry", 12.99, true);
        insertMenuItem("ITEM02", "Margherita Pizza", "Classic pizza with mozzarella and basil", 9.99, true);
        insertMenuItem("ITEM03", "California Roll", "Sushi with crab, avocado, and cucumber", 8.99, true);
        insertMenuItem("ITEM04", "Tacos al Pastor", "Tacos with marinated pork", 7.49, true);
        insertMenuItem("ITEM05", "Cheeseburger", "Juicy burger with cheese", 10.49, true);

        // Insert orders
        insertOrder("USER01", "RESTA1", "DELIVERED");
        insertOrder("USER02", "RESTA2", "PENDING");
        insertOrder("USER03", "RESTA3", "CANCELLED");
        insertOrder("USER04", "RESTA4", "IN_PROGRESS");
        insertOrder("USER05", "RESTA5", "DELIVERED");

        // Insert cart items
        insertCartItem("USER01", "ITEM01", 2);
        insertCartItem("USER02", "ITEM02", 1);
        insertCartItem("USER03", "ITEM03", 3);
    }

    private void insertUser(String name, String email, String password, String role) {
        int rows = jdbcTemplate.update("INSERT INTO users (id, name, email, password, role) VALUES (?, ?, ?, ?, ?)",
                uuidService.generateShortUUID(), name, email, password, role);
        if (rows > 0) {
            System.out.println("Inserted user: " + name);
        } else {
            System.out.println("Failed to insert user: " + name);
        }
    }

    private void insertRestaurant(String name, String address, String cuisine, double rating) {
        int rows = jdbcTemplate.update("INSERT INTO restaurants (id, name, address, cuisine, rating) VALUES (?, ?, ?, ?, ?)",
                uuidService.generateShortUUID(), name, address, cuisine, rating);
        if (rows > 0) {
            System.out.println("Inserted restaurant: " + name);
        } else {
            System.out.println("Failed to insert restaurant: " + name);
        }
    }

    private void insertMenuItem(String itemId, String itemName, String description, double price, boolean availability) {
        int rows = jdbcTemplate.update("INSERT INTO menu (id, restaurant_id, item_name, description, price, availability) VALUES (?, ?, ?, ?, ?, ?)",
                itemId, "RESTA1", itemName, description, price, availability);
        if (rows > 0) {
            System.out.println("Inserted menu item: " + itemName);
        } else {
            System.out.println("Failed to insert menu item: " + itemName);
        }
    }

    private void insertOrder(String userId, String restaurantId, String status) {
        int rows = jdbcTemplate.update("INSERT INTO orders (id, user_id, restaurant_id, order_date, status) VALUES (?, ?, ?, NOW(), ?)",
                uuidService.generateShortUUID(), userId, restaurantId, status);
        if (rows > 0) {
            System.out.println("Inserted order for user ID: " + userId);
        } else {
            System.out.println("Failed to insert order for user ID: " + userId);
        }
    }

    private void insertCartItem(String userId, String menuItemId, int quantity) {
        int rows = jdbcTemplate.update("INSERT INTO cart_items (id, user_id, menu_item_id, quantity) VALUES (?, ?, ?, ?)",
                uuidService.generateShortUUID(), userId, menuItemId, quantity);
        if (rows > 0) {
            System.out.println("Inserted cart item for user ID: " + userId);
        } else {
            System.out.println("Failed to insert cart item for user ID: " + userId);
        }
    }
}
