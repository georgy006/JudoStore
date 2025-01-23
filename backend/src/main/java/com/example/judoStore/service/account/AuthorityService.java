package com.example.judoStore.service.account;

import com.example.judoStore.persistence.models.Customer;

public interface AuthorityService {

    Customer getCurrentCustomer();

    Long getCurrentCustomerId();

//    boolean hasAnyAuthority(List<RoleName> roles);
//
//    void checkRolesAndThrow(List<RoleName> roles);
}
