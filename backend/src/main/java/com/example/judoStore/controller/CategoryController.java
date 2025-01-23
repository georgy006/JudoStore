package com.example.judoStore.controller;

import com.example.judoStore.persistence.models.Category;

import com.example.judoStore.requests.CreateCategoryRequest;
import com.example.judoStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping(path = "/category")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/category/{id}")
    public Category getCategoryById(@PathVariable(name = "id") Long id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping(path = "/category/{id}")
    public void deleteCategoryById(@PathVariable(name = "id") Long id){
        categoryService.deleteCategoryById(id);
    }

    @PostMapping(path = "/category")
    public Category createCategory(@RequestBody CreateCategoryRequest request){
        return categoryService.createCategory(request);
    }

}
