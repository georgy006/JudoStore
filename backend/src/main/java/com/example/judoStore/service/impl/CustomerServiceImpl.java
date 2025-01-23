package com.example.judoStore.service.impl;

import com.example.judoStore.persistence.models.Customer;
import com.example.judoStore.persistence.repository.CustomerRepository;
import com.example.judoStore.requests.CreateCustomerRequest;
import com.example.judoStore.responses.CustomerInfoResponse;
import com.example.judoStore.responses.dto.CustomerInfoDto;
import com.example.judoStore.service.CustomerService;
import com.example.judoStore.service.account.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.getCustomerByEmail(username).get();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerInfoResponse getCustomerInfo() {
        Customer customer = authorityService.getCurrentCustomer();
        if(customer != null){
            CustomerInfoDto customerInfoDto = new CustomerInfoDto(
                    customer.getName(),
                    customer.getEmail(),
                    customer.getCity(),
                    customer.getPhoneNumber()
            );
            return new CustomerInfoResponse(customerInfoDto);
        }
        throw new RuntimeException();
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email).get();
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
       customerRepository.save(customer);
       return customer;
    }

    @Override
    public Customer updateCustomerById(Long id, CreateCustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setCity(request.getCity());
        customer.setPhoneNumber(request.getPhoneNumber());
        customerRepository.save(customer);
        return customer;
    }


}
