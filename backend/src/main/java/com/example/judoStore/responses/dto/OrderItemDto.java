package com.example.judoStore.responses.dto;

public record OrderItemDto(
        ProductDto productDto,
        Long quantity
) {
}
