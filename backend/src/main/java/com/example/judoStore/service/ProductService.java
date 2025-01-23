package com.example.judoStore.service;

import com.example.judoStore.persistence.models.Product;
import com.example.judoStore.requests.CreateProductRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product createProduct(CreateProductRequest request);
    List<Product> getProductsSortedByName(String sortDirection, Boolean sortByName);
}
