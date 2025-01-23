package com.example.judoStore.service.impl;

import com.example.judoStore.persistence.models.*;
import com.example.judoStore.persistence.repository.CartRepository;
import com.example.judoStore.persistence.repository.CustomerRepository;
import com.example.judoStore.persistence.repository.ProductRepository;
import com.example.judoStore.persistence.repository.ProductsCartRepository;
import com.example.judoStore.responses.CartResponse;
import com.example.judoStore.responses.dto.ProductsCartDto;
import com.example.judoStore.service.CartService;
import com.example.judoStore.service.account.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductsCartRepository productsCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Cart updateCart(Customer customer) {
        Cart cart = new Cart();
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());

        if(customerOptional.isPresent()){
            cart.setCustomer(customerOptional.get());
        }
        return cartRepository.save(cart);
    }

    @Override
    public CartResponse getCart() {
        return getProductsCartDtos(getCurrentCart());
    }
    @Override
    public Cart getCartById(Long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if(cartOptional.isPresent()){
            return cartOptional.get();
        }
        throw new RuntimeException();
    }
    @Override
    @Transactional
    public void deleteCartById(Long productId) {
        deleteProductById(productId);
    }

    @Override
    @Transactional
    public void deleteProductsFromCart() {
        Long cartId = cartRepository.getCartByCustomerId(authorityService.getCurrentCustomerId()).getId();
        productsCartRepository.deleteProductsByCartId(cartId);
    }

    private void deleteProductById(Long productId){
        Long cartId = cartRepository.getCartByCustomerId(authorityService.getCurrentCustomerId()).getId();
        productsCartRepository.deleteByCartIdAndProductId(cartId, productId);
    }

    @Override
    public CartResponse addProductToCart(Long productId, Long quantity) {
        Customer customer = authorityService.getCurrentCustomer();
        Long cartId = cartRepository.getCartByCustomerId(customer.getId()).getId();
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        ProductsCart record = productsCartRepository.getProductsCartByCartIdAndProductId(cartId, productId);
        if (record != null) {
            record.setQuantity(record.getQuantity() + quantity);
        } else {
            record = new ProductsCart();
            record.setCart(cart);
            record.setProduct(product);
            record.setQuantity(quantity);
        }
        productsCartRepository.save(record);
        return getProductsCartDtos(cart);
    }

    @Override
    public Long changeProductAmount(Long productId, Boolean increase) {
        Cart cart = cartRepository.getCartByCustomerId(authorityService.getCurrentCustomer().getId());
        Optional<ProductsCart> product = cart.getProductsCarts().stream()
                .filter(pc -> pc.getProduct().getId().equals(productId))
                .findFirst();
        if(product.isEmpty()){
            return 0L;
        }
        Long quantity = product.get().getQuantity();
        if(increase){
            quantity++;
        }else{
            if(quantity.equals(1L)){
                deleteProductById(productId);
                return 0L;
            }
            quantity--;
        }
        product.get().setQuantity(quantity);
        productsCartRepository.save(product.get());
        return quantity;
    }



    public Long getCartIdByCustomerId(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        return cart != null ? cart.getId() : null;
    }

    @Override
    public Cart getCurrentCustomerCart() {
        return getCurrentCart();
    }
    private Cart getCurrentCart(){
        return cartRepository.getCartByCustomerId(authorityService.getCurrentCustomer().getId());
    }
    private static CartResponse getProductsCartDtos(Cart cart) {
        List<ProductsCartDto> products = cart.getProductsCarts().stream()
                .map(productsCart ->
                        new ProductsCartDto(
                                productsCart.getCart().getId(),
                                productsCart.getProduct(),
                                productsCart.getQuantity())
                )
                .toList();
        return new CartResponse(
                cart.getCustomer().getId(),
                products
        );
    }
}
