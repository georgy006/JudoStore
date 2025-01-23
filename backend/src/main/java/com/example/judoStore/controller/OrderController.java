package com.example.judoStore.controller;

import com.example.judoStore.responses.dto.OrderDto;
import com.example.judoStore.service.CartService;
import com.example.judoStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @PostMapping("/order")
    public OrderDto createOrder(){
        OrderDto order = orderService.createOrder();
        cartService.deleteProductsFromCart();
        return order;
    }

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

}
