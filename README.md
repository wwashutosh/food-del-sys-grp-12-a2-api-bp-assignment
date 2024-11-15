# API BASED PRODUCTS - ASSIGNMENT-2
# Food Delivery System API

# App Successfully deployed at
## https://food-delivery-app-latest.onrender.com

## Overview
This project is a backend API for a Food Delivery System developed in Java with Spring Boot. It enables different user roles—customers, restaurant owners, delivery personnel, and administrators—to interact with the system according to specific permissions and functionalities. The API supports browsing restaurants, placing and tracking orders, managing menus, and tracking delivery activities. It includes security features such as role-based access control and token-based authentication.

## Features

### Customer Functionalities
- **Register/Login**: Account creation, login, and profile management.
- **Browse Restaurants**: View restaurants and their menus.
- **Search Menus**: Filter and search for food items.
- **Place an Order**: Add items to a cart and place orders.
- **Track Orders**: Monitor order status.
- **Order History**: View and reorder past orders.

### Restaurant Owner Functionalities
- **Register/Login**: Account management.
- **Manage Menus**: Add, update, or remove menu items.
- **View Orders**: Manage incoming orders and update their statuses.
- **Restaurant Details**: Update restaurant information.

### Delivery Personnel Functionalities
- **Register/Login**: Profile and availability management.
- **View and Accept Deliveries**: Manage delivery assignments.
- **Track Delivery Status**: Update the progress of each delivery.

### Administrator Functionalities
- **User Management**: Create, update, or deactivate user accounts.
- **Order Management**: View and manage platform orders.
- **Generate Reports**: Generate performance reports on the system.
- **Monitor Platform Activity**: Track platform health and activity.

## Non-Functional Requirements
- **Role-Based Access Control**: Secures access based on user roles.
- **Token-Based Authentication**: Ensures secure access using JWT.
- **Data Validation and Error Handling**: Validates inputs and handles errors gracefully.
- **API Documentation**: Each endpoint is documented with Postman.

## Tech Stack
- **Backend**: Java, Spring Boot
- **Database**: H2 (for development and testing)
- **Authentication**: JWT for secure token-based authentication
- **API Documentation**: Postman for endpoint documentation

## Setup and Installation

1. **Clone the Repository**:
   ```bash
   git clone  https://github.com/wwashutosh/food-del-sys-grp-12-a2-api-bp-assignment.git
2. **Add configuration in IntelliJ** with the specified Main Class of the Application
3. Run the Application and access the APIs at
   ```
   http://localhost:8080
