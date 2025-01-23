package com.example.judoStore.service;

import com.example.judoStore.responses.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder();
    List<OrderDto> getAllOrders();

}
