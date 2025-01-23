package com.example.judoStore.controller;

import com.example.judoStore.persistence.models.Product;
import com.example.judoStore.persistence.repository.ProductRepository;
import com.example.judoStore.requests.CreateProductRequest;
import com.example.judoStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping(path = "/product/{id}")
    public Product getProductById(@PathVariable(name = "id") Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping(path = "/product/{id}")
    public void deleteProductById(@PathVariable(name = "id") Long id){
        productService.deleteProductById(id);
    }

    @PostMapping(path = "/product")
    public Product createProduct(@RequestBody CreateProductRequest request){
        return productService.createProduct(request);
    }

    @GetMapping("/product/filter")
    public List<Product> filterProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return productRepository.filterProducts(categoryId, minPrice, maxPrice);
    }

    @GetMapping("/product/sort")
    public List<Product> getProductsSortedByName(@RequestParam(defaultValue = "ASC") String sortDirection,
                                                 @RequestParam(defaultValue = "false") Boolean sortByName) {
        return productService.getProductsSortedByName(sortDirection, sortByName);
    }
}
