package com.example.judoStore.responses;

import com.example.judoStore.responses.dto.ProductsCartDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CartResponse(
    Long customerId,
    List<ProductsCartDto> products
) {}
