package com.example.judoStore.service.impl;

import com.example.judoStore.persistence.models.Cart;
import com.example.judoStore.persistence.models.Order;
import com.example.judoStore.persistence.models.OrderItem;
import com.example.judoStore.persistence.models.OrderStatus;
import com.example.judoStore.persistence.repository.OrderRepository;
import com.example.judoStore.persistence.repository.OrderStatusRepository;
import com.example.judoStore.responses.dto.OrderDto;
import com.example.judoStore.responses.dto.OrderItemDto;
import com.example.judoStore.responses.dto.ProductDto;
import com.example.judoStore.service.CartService;
import com.example.judoStore.service.OrderService;
import com.example.judoStore.service.account.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AuthorityService authorityService;

    @Override
    public OrderDto createOrder() {
        Cart cart = cartService.getCurrentCustomerCart();
        Order order = new Order();

        List<OrderItem> orderItems = cart.getProductsCarts().stream()
                .map(productsCart -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(productsCart.getProduct());
                    orderItem.setQuantity(productsCart.getQuantity());
                    return orderItem;
                })
                .toList();


        BigDecimal totalPrice = orderItems.stream()
                .map(oi -> oi.getProduct().getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderItems(orderItems);
        order.setOrdersDate(Timestamp.valueOf(LocalDateTime.now()));
        order.setOrderStatus(orderStatusRepository.getOrderStatusByName(OrderStatus.WAITING));
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        List<OrderItemDto> orderItemDtos = order.getOrderItems().stream().map(orderItem -> {
            ProductDto productDto = new ProductDto(
                    orderItem.getProduct().getProductName(),
                    orderItem.getProduct().getPrice(),
                    orderItem.getProduct().getCategory()
            );
            return new OrderItemDto(
                    productDto,
                    orderItem.getQuantity()
            );
        }).toList();
        return new OrderDto(
                order.getCustomer().getName(),
                orderItemDtos,
                order.getOrdersDate(),
                order.getTotalPrice()
        );
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.getOrdersByCustomerId(authorityService.getCurrentCustomer().getId());

        return orders.stream().map(order -> {
            List<OrderItemDto> orderItemDtos = order.getOrderItems().stream().map(orderItem -> {
                ProductDto productDto = new ProductDto(
                        orderItem.getProduct().getProductName(),
                        orderItem.getProduct().getPrice(),
                        orderItem.getProduct().getCategory()
                );
                return new OrderItemDto(
                        productDto,
                        orderItem.getQuantity()
                );
            }).collect(Collectors.toList());
            String customerName = order.getCustomer() != null ? order.getCustomer().getName() : "Unknown Customer";
            return new OrderDto(
                    customerName,
                    orderItemDtos,
                    order.getOrdersDate(),
                    order.getTotalPrice()
            );
        }).collect(Collectors.toList());




    }

}
