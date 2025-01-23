package com.example.judoStore.service;

import com.example.judoStore.persistence.models.Cart;
import com.example.judoStore.persistence.models.Customer;
import com.example.judoStore.responses.CartResponse;

public interface CartService {
    Cart updateCart(Customer customer);
    CartResponse getCart();
    Cart getCartById(Long id);
    void deleteCartById(Long id);
    void deleteProductsFromCart();
    CartResponse addProductToCart(Long productId, Long quantity);
    Long changeProductAmount(Long productId, Boolean increase);
    Long getCartIdByCustomerId(Long customerId);
    Cart getCurrentCustomerCart();

}
