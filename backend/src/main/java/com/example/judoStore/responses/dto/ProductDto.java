package com.example.judoStore.responses.dto;

import com.example.judoStore.persistence.models.Category;

import java.math.BigDecimal;

public record ProductDto(
         String productName,
         BigDecimal price,
         Category category
) {
}
