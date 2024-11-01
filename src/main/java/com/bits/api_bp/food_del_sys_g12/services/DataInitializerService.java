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
        jdbcTemplate.execute("DROP TABLE IF EXISTS orders;");
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

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS orders (" +
                "id CHAR(6) PRIMARY KEY," +
                "user_id CHAR(6)," +
                "restaurant_id CHAR(6)," +
                "order_date TIMESTAMP," +
                "status VARCHAR(50)" +
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

        // Insert orders
        insertOrder("USER01", "RESTA1", "DELIVERED");
        insertOrder("USER02", "RESTA2", "PENDING");
        insertOrder("USER03", "RESTA3", "CANCELLED");
        insertOrder("USER04", "RESTA4", "IN_PROGRESS");
        insertOrder("USER05", "RESTA5", "DELIVERED");
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

    private void insertOrder(String userId, String restaurantId, String status) {
        int rows = jdbcTemplate.update("INSERT INTO orders (id, user_id, restaurant_id, order_date, status) VALUES (?, ?, ?, NOW(), ?)",
                uuidService.generateShortUUID(), userId, restaurantId, status);
        if (rows > 0) {
            System.out.println("Inserted order for user ID: " + userId);
        } else {
            System.out.println("Failed to insert order for user ID: " + userId);
        }
    }
}
