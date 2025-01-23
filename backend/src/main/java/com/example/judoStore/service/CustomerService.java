package com.example.judoStore.service;

import com.example.judoStore.persistence.models.Customer;
import com.example.judoStore.requests.CreateCustomerRequest;
import com.example.judoStore.responses.CustomerInfoResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService {
    List<Customer> getAllCustomers();
    CustomerInfoResponse getCustomerInfo();
    Customer getCustomerByEmail(String email);
    void deleteCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomerById(Long id, CreateCustomerRequest customerRequest);
}
