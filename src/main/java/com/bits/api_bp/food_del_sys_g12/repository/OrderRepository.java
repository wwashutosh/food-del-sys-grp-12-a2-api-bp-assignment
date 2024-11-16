package com.bits.api_bp.food_del_sys_g12.repository;

import com.bits.api_bp.food_del_sys_g12.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    // Find orders for a specific restaurant
    List<OrderEntity> findByRestaurantId(String restaurantId);

    // Find past orders for a specific customer/user
    List<OrderEntity> findByUserId(String userId);

    // Find orders assigned to a delivery personnel that are not delivered yet
    List<OrderEntity> findByDeliveryPersonnelIdAndStatusNot(String deliveryPersonnelId, String status);

    // Find completed (delivered) orders for a delivery personnel
    List<OrderEntity> findByDeliveryPersonnelIdAndStatus(String deliveryPersonnelId, String status);

    long countByStatus(String status);

    @Query("SELECT o.restaurantId, COUNT(o) AS orderCount FROM OrderEntity o GROUP BY o.restaurantId ORDER BY orderCount DESC")
    List<Object[]> findPopularRestaurants();

    @Query("SELECT AVG(TIMESTAMPDIFF(SECOND, o.orderDate, o.deliveryDate)) / 60.0 FROM OrderEntity o WHERE o.status = 'DELIVERED'")
    double calculateAverageDeliveryTime();

    @Query("SELECT FUNCTION('FORMATDATETIME', o.orderDate, 'yyyy-MM-dd') AS orderDate, COUNT(o) AS orderCount " +
            "FROM OrderEntity o " +
            "GROUP BY FUNCTION('FORMATDATETIME', o.orderDate, 'yyyy-MM-dd')")
    List<Object[]> findOrderTrends();
}
