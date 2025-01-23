package com.example.judoStore.persistence.repository;

import com.example.judoStore.persistence.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartByCustomerId(Long customerId);
    Cart findByCustomerId(Long customerId);

}
