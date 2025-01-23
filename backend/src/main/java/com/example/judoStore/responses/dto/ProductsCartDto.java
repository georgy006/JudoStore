package com.example.judoStore.responses.dto;

import com.example.judoStore.persistence.models.Product;

public record ProductsCartDto (
    Long cartId,

    Product product,

    Long quantity
) {}
