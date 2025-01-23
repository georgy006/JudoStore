package com.example.judoStore.service;

import com.example.judoStore.persistence.models.Category;
import com.example.judoStore.persistence.models.Customer;
import com.example.judoStore.requests.CreateCategoryRequest;
import com.example.judoStore.requests.CreateCustomerRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void deleteCategoryById(Long id);
    Category createCategory(CreateCategoryRequest name);

}
