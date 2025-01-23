package com.example.judoStore.service.account.impl;

import com.example.judoStore.persistence.models.Customer;
import com.example.judoStore.service.account.AuthorityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    @Override
    public Customer getCurrentCustomer() {
        return getCustomer();
    }

    @Override
    public Long getCurrentCustomerId() {
        return getCustomer().getId();
    }

    private Customer getCustomer(){
        return (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

//    @Override
//    public boolean hasAnyAuthority(List<RoleName> roles) {
//        List<String> roleNames = roles.stream().map(RoleName::name).toList();
//        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
//                .anyMatch(authority -> roleNames.contains(authority.getAuthority()));
//    }
//
//    @Override
//    public void checkRolesAndThrow(List<RoleName> roles) {
//        if (!hasAnyAuthority(roles)) {
//            throw new NoAuthorityException();
//        }
//    }

}