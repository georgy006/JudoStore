package com.example.judoStore.service.account.impl;

import com.example.judoStore.persistence.models.Customer;
import com.example.judoStore.persistence.models.token.BlacklistedToken;
import com.example.judoStore.persistence.repository.BlacklistedTokenRepository;
import com.example.judoStore.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlacklistServiceImpl {
    private final BlacklistedTokenRepository blacklistedTokenRepository;
    private final CustomerRepository customerRepository;

    public void add(String token, LocalDateTime expiryDate, Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customerId));

        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpiryDate(expiryDate);
        blacklistedToken.setCustomer(customer);

        blacklistedTokenRepository.save(blacklistedToken);
    }

    public boolean isBlacklisted(String token) {
        return blacklistedTokenRepository.findByToken(token).isPresent();
    }
}
