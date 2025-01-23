package com.example.judoStore.controller;

import com.example.judoStore.persistence.models.Cart;
import com.example.judoStore.persistence.models.Customer;
import com.example.judoStore.requests.AuthenticationRequestDto;
import com.example.judoStore.requests.CreateCustomerRequest;
import com.example.judoStore.responses.AuthenticationResponse;
import com.example.judoStore.responses.CustomerInfoResponse;
import com.example.judoStore.responses.dto.CustomerInfoDto;
import com.example.judoStore.service.CartService;
import com.example.judoStore.service.CustomerService;
import com.example.judoStore.service.account.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;
    @Autowired
    CartService cartService;

    @GetMapping(path = "/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "/customer")
    public CustomerInfoResponse getCustomer(){
        return customerService.getCustomerInfo();
    }

    @DeleteMapping(path = "/customer/{id}")
    public void deleteCustomerById(@PathVariable(name = "id") Long id){
        customerService.deleteCustomerById(id);
    }

    @PostMapping(path = "/customer/{id}")
    public Customer updateCustomerById(@PathVariable(name = "id") Long id,@RequestBody CreateCustomerRequest request){
        return customerService.updateCustomerById(id,request);
    }

    @PostMapping(path = "/customer/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequestDto request){
        return accountService.authenticate(request);
    }

    @PostMapping(path = "/customer/register")
    public Customer register(@RequestBody CreateCustomerRequest request){
        Customer customer = accountService.register(request);
        cartService.updateCart(customer);
        return customer;
    }

    @PostMapping(path = "/customers/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        accountService.logout(request);
        return ResponseEntity.noContent().build();
    }
}
