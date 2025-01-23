package com.example.judoStore.persistence.repository;

import com.example.judoStore.persistence.models.ProductsCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCartRepository extends JpaRepository<ProductsCart, Long> {

    ProductsCart getProductsCartByCartIdAndProductId(Long cartId, Long productId);
    void deleteByCartIdAndProductId(Long cartId, Long productId);
    void deleteProductsByCartId(Long cartId);

}
