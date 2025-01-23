package com.example.judoStore.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String productName;
    private BigDecimal price;
    private Long stockQuantity;
    private Long categoryId;
}
