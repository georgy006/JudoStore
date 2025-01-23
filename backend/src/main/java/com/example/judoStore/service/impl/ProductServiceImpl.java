package com.example.judoStore.service.impl;

import com.example.judoStore.persistence.models.Category;
import com.example.judoStore.persistence.models.Product;
import com.example.judoStore.persistence.repository.CategoryRepository;
import com.example.judoStore.persistence.repository.ProductRepository;
import com.example.judoStore.requests.CreateProductRequest;
import com.example.judoStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        throw new RuntimeException();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());

        Optional<Category> categoryOptional = categoryRepository.findById(request.getCategoryId());

        if(categoryOptional.isPresent()){
            product.setCategory(categoryOptional.get());
        }
        productRepository.save(product);
        return product;
    }

    public List<Product> getProductsSortedByName(String sortDirection, Boolean sortByName) {
        if (sortByName) {
            Sort sort = Sort.by("productName");
            if ("DESC".equalsIgnoreCase(sortDirection)) {
                sort = sort.descending();
            } else {
                sort = sort.ascending();
            }
            return productRepository.findAll(sort);
        }else {
            Sort sort = Sort.by("price");
            if ("DESC".equalsIgnoreCase(sortDirection)) {
                sort = sort.descending();
            } else {
                sort = sort.ascending();
            }
            return productRepository.findAll(sort);
        }
    }
}
