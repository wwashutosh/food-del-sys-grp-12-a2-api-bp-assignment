package com.bits.api_bp.food_del_sys_g12.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataInitializerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initializeDatabase() {
        dropTables();
        createTables();
        insertRestaurants();
        insertUsers();
        insertDeliveryPersonnel();
        insertMenuItems();
        insertCartItems();
        insertOrders();
    }

    private void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS CART_ITEMS CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS DELIVERY_PERSONNEL CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS MENU CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS ORDERS CASCADE");
        jdbcTemplate.execute("DROP TABLE IF EXISTS RESTAURANTS CASCADE");

    }

    private void createTables() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS RESTAURANTS (" +
                "ID CHARACTER VARYING(255) PRIMARY KEY, " +
                "ADDRESS CHARACTER VARYING(255), " +
                "CUISINE CHARACTER VARYING(255), " +
                "NAME CHARACTER VARYING(255), " +
                "RATING FLOAT(53)" +
                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS CART_ITEMS (" +
                "ID CHARACTER VARYING(255) PRIMARY KEY, " +
                "MENU_ITEM_ID CHARACTER VARYING(255), " +
                "QUANTITY INTEGER, " +
                "USERID CHARACTER VARYING(255)" +
                ")");

        // Create other tables similarly...

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS DELIVERY_PERSONNEL (" +
                "ID CHARACTER VARYING(255) PRIMARY KEY, " +
                "AVAILABILITY BOOLEAN" +
                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS MENU (" +
                "ID CHARACTER VARYING(255) PRIMARY KEY, " +
                "AVAILABILITY BOOLEAN, " +
                "DESCRIPTION CHARACTER VARYING(255), " +
                "NAME CHARACTER VARYING(255), " +
                "PRICE FLOAT(53), " +
                "RESTAURANT_ID CHARACTER VARYING(255)" +
                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS ORDERS (" +
                "ID CHARACTER VARYING(255) PRIMARY KEY, " +
                "DELIVERY_DATE TIMESTAMP, " +
                "DELIVERY_PERSONNEL_ID CHARACTER VARYING(255), " +
                "ORDER_DATE TIMESTAMP, " +
                "RESTAURANT_ID CHARACTER VARYING(255), " +
                "STATUS CHARACTER VARYING(255), " +
                "USERID CHARACTER VARYING(255)" +
                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS TOKEN (" +
                "USERID CHARACTER VARYING(255) PRIMARY KEY" +
                ")");

        // USERS table should already exist and thus is not recreated.
    }

    private void insertRestaurants() {
        jdbcTemplate.update("INSERT INTO RESTAURANTS (ID, ADDRESS, CUISINE, NAME, RATING) VALUES ('r1', '123 Main St', 'Italian', 'Pasta Palace', 4.5)");
        jdbcTemplate.update("INSERT INTO RESTAURANTS (ID, ADDRESS, CUISINE, NAME, RATING) VALUES ('r2', '456 Elm St', 'American', 'Burger Barn', 4.2)");
        jdbcTemplate.update("INSERT INTO RESTAURANTS (ID, ADDRESS, CUISINE, NAME, RATING) VALUES ('r3', '789 Oak St', 'Mexican', 'Taco Town', 4.0)");
        jdbcTemplate.update("INSERT INTO RESTAURANTS (ID, ADDRESS, CUISINE, NAME, RATING) VALUES ('r4', '101 Pine St', 'Chinese', 'Wok Wonders', 4.3)");
        jdbcTemplate.update("INSERT INTO RESTAURANTS (ID, ADDRESS, CUISINE, NAME, RATING) VALUES ('r5', '202 Maple St', 'Indian', 'Curry Corner', 4.4)");
    }

    private void insertUsers() {
        // Customer Users
        jdbcTemplate.update("INSERT INTO USERS (USERID, ACTIVE, ADDRESS, EMAILADDRESS, NAME, PASSWORD, PHONE, ROLE) VALUES ('u1', true, '111 Customer St', 'customer1@example.com', 'Alice', 'pass123', '1234567890', 'CUSTOMER')");
        jdbcTemplate.update("INSERT INTO USERS (USERID, ACTIVE, ADDRESS, EMAILADDRESS, NAME, PASSWORD, PHONE, ROLE) VALUES ('u2', true, '222 Customer St', 'customer2@example.com', 'Bob', 'pass456', '0987654321', 'CUSTOMER')");

        // Restaurant Owner Users
        jdbcTemplate.update("INSERT INTO USERS (USERID, ACTIVE, ADDRESS, EMAILADDRESS, NAME, PASSWORD, PHONE, RESTAURANT_HOURS, ROLE) VALUES ('u3', true, '333 Restaurant St', 'owner1@example.com', 'Charlie', 'pass789', '1122334455', '9 AM - 9 PM', 'RESTAURANT_OWNER')");
        jdbcTemplate.update("INSERT INTO USERS (USERID, ACTIVE, ADDRESS, EMAILADDRESS, NAME, PASSWORD, PHONE, RESTAURANT_HOURS, ROLE) VALUES ('u4', true, '444 Restaurant St', 'owner2@example.com', 'David', 'pass101', '6677889900', '10 AM - 8 PM', 'RESTAURANT_OWNER')");

        // Delivery Personnel Users
        jdbcTemplate.update("INSERT INTO USERS (USERID, ACTIVE, ADDRESS, DELIVERY_VEHICLE, EMAILADDRESS, NAME, PASSWORD, PHONE, ROLE) VALUES ('u5', true, '555 Delivery St', 'Bike', 'delivery1@example.com', 'Eve', 'pass202', '3344556677', 'DELIVERY_PERSONNEL')");
        jdbcTemplate.update("INSERT INTO USERS (USERID, ACTIVE, ADDRESS, DELIVERY_VEHICLE, EMAILADDRESS, NAME, PASSWORD, PHONE, ROLE) VALUES ('u6', true, '666 Delivery St', 'Car', 'delivery2@example.com', 'Frank', 'pass303', '5566778899', 'DELIVERY_PERSONNEL')");

        // Admin User
        jdbcTemplate.update("INSERT INTO USERS (USERID, ACTIVE, ADDRESS, EMAILADDRESS, NAME, PASSWORD, PHONE, ROLE) VALUES ('u7', true, '777 Admin St', 'admin@example.com', 'Grace', 'pass404', '7788990011', 'ADMIN')");
    }

    private void insertDeliveryPersonnel() {
        jdbcTemplate.update("INSERT INTO DELIVERY_PERSONNEL (ID, AVAILABILITY) VALUES ('dp1', true)");
        jdbcTemplate.update("INSERT INTO DELIVERY_PERSONNEL (ID, AVAILABILITY) VALUES ('dp2', false)");
        jdbcTemplate.update("INSERT INTO DELIVERY_PERSONNEL (ID, AVAILABILITY) VALUES ('dp3', true)");
        jdbcTemplate.update("INSERT INTO DELIVERY_PERSONNEL (ID, AVAILABILITY) VALUES ('dp4', true)");
        jdbcTemplate.update("INSERT INTO DELIVERY_PERSONNEL (ID, AVAILABILITY) VALUES ('dp5', false)");
    }

    private void insertMenuItems() {
        jdbcTemplate.update("INSERT INTO MENU (ID, AVAILABILITY, DESCRIPTION, NAME, PRICE, RESTAURANT_ID) VALUES ('m1', true, 'Spicy Chicken', 'Chicken Dish', 12.99, 'r1')");
        jdbcTemplate.update("INSERT INTO MENU (ID, AVAILABILITY, DESCRIPTION, NAME, PRICE, RESTAURANT_ID) VALUES ('m2', true, 'Veggie Delight', 'Vegetable Salad', 9.99, 'r1')");
        jdbcTemplate.update("INSERT INTO MENU (ID, AVAILABILITY, DESCRIPTION, NAME, PRICE, RESTAURANT_ID) VALUES ('m3', false, 'Beef Burger', 'Burger', 11.49, 'r2')");
        jdbcTemplate.update("INSERT INTO MENU (ID, AVAILABILITY, DESCRIPTION, NAME, PRICE, RESTAURANT_ID) VALUES ('m4', true, 'Pepperoni Pizza', 'Pizza', 15.99, 'r2')");
        jdbcTemplate.update("INSERT INTO MENU (ID, AVAILABILITY, DESCRIPTION, NAME, PRICE, RESTAURANT_ID) VALUES ('m5', true, 'Pasta Primavera', 'Pasta', 13.50, 'r3')");
    }

    private void insertCartItems() {
        jdbcTemplate.update("INSERT INTO CART_ITEMS (ID, MENU_ITEM_ID, QUANTITY, USERID) VALUES ('c1', 'm1', 2, 'u1')");
        jdbcTemplate.update("INSERT INTO CART_ITEMS (ID, MENU_ITEM_ID, QUANTITY, USERID) VALUES ('c2', 'm2', 1, 'u1')");
        jdbcTemplate.update("INSERT INTO CART_ITEMS (ID, MENU_ITEM_ID, QUANTITY, USERID) VALUES ('c3', 'm3', 3, 'u2')");
        jdbcTemplate.update("INSERT INTO CART_ITEMS (ID, MENU_ITEM_ID, QUANTITY, USERID) VALUES ('c4', 'm4', 1, 'u3')");
        jdbcTemplate.update("INSERT INTO CART_ITEMS (ID, MENU_ITEM_ID, QUANTITY, USERID) VALUES ('c5', 'm5', 4, 'u4')");
    }

    private void insertOrders() {
        jdbcTemplate.update("INSERT INTO ORDERS (ID, DELIVERY_DATE, DELIVERY_PERSONNEL_ID, ORDER_DATE, RESTAURANT_ID, STATUS, USERID) VALUES ('o1', CURRENT_TIMESTAMP, 'dp1', CURRENT_TIMESTAMP, 'r1', 'DELIVERED', 'u1')");
        jdbcTemplate.update("INSERT INTO ORDERS (ID, DELIVERY_DATE, DELIVERY_PERSONNEL_ID, ORDER_DATE, RESTAURANT_ID, STATUS, USERID) VALUES ('o2', CURRENT_TIMESTAMP, 'dp2', CURRENT_TIMESTAMP, 'r2', 'PENDING', 'u1')");
        jdbcTemplate.update("INSERT INTO ORDERS (ID, DELIVERY_DATE, DELIVERY_PERSONNEL_ID, ORDER_DATE, RESTAURANT_ID, STATUS, USERID) VALUES ('o3', CURRENT_TIMESTAMP, 'dp3', CURRENT_TIMESTAMP, 'r2', 'DELIVERED', 'u2')");
        jdbcTemplate.update("INSERT INTO ORDERS (ID, DELIVERY_DATE, DELIVERY_PERSONNEL_ID, ORDER_DATE, RESTAURANT_ID, STATUS, USERID) VALUES ('o4', CURRENT_TIMESTAMP, 'dp4', CURRENT_TIMESTAMP, 'r3', 'CANCELLED', 'u3')");
        jdbcTemplate.update("INSERT INTO ORDERS (ID, DELIVERY_DATE, DELIVERY_PERSONNEL_ID, ORDER_DATE, RESTAURANT_ID, STATUS, USERID) VALUES ('o5', CURRENT_TIMESTAMP, 'dp5', CURRENT_TIMESTAMP, 'r3', 'PENDING', 'u4')");
    }
}
