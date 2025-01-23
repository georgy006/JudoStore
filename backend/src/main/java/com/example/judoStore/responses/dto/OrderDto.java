package com.example.judoStore.responses.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


public record OrderDto (
        String customerName,
        List<OrderItemDto> orderItemDtos,
        Timestamp dateTime,
        BigDecimal totalPrice
) {
}
