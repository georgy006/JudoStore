package com.example.judoStore.controller;

import com.example.judoStore.persistence.models.Cart;
import com.example.judoStore.requests.CreateCartRequest;
import com.example.judoStore.responses.CartResponse;
import com.example.judoStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public CartResponse getCart() {
        return cartService.getCart();
    }

    @GetMapping("/cart/{id}")
    public Cart getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

//    @PostMapping("/create-cart")
//    public Cart createCart(@RequestBody CreateCartRequest request) {
//        return cartService.updateCart();
//    }

    @DeleteMapping("/cart")
    public ResponseEntity<Void> deleteProduct(@RequestParam Long productId) {
        cartService.deleteCartById(productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cart")
    public CartResponse addProductToCart(@RequestParam Long productId,
                                         @RequestParam Long quantity) {
        return cartService.addProductToCart(productId, quantity);
    }

    @PostMapping("/cart/changeProductAmount")
    public Long changeProductAmount(@RequestParam Long productId,
                                         @RequestParam Boolean increase) {
        return cartService.changeProductAmount(productId, increase);
    }

    @GetMapping("/getCartId")
    public ResponseEntity<Long> getCartId(@RequestParam Long customerId) {
        Long cartId = cartService.getCartIdByCustomerId(customerId);
        if (cartId != null) {
            return ResponseEntity.ok(cartId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
