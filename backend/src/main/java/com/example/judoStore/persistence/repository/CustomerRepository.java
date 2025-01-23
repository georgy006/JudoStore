package com.example.judoStore.persistence.repository;

import com.example.judoStore.persistence.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> getCustomerByEmail(String email);

}
