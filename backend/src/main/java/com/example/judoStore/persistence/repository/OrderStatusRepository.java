package com.example.judoStore.persistence.repository;

import com.example.judoStore.persistence.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    OrderStatus getOrderStatusByName(String name);
}
