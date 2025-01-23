package com.example.judoStore.persistence.repository;

import com.example.judoStore.persistence.models.Order;
import com.example.judoStore.responses.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> getOrdersByCustomerId(Long customerId);
}
