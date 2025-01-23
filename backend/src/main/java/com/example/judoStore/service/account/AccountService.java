package com.example.judoStore.service.account;

import com.example.judoStore.persistence.models.Customer;
import com.example.judoStore.requests.AuthenticationRequestDto;
import com.example.judoStore.requests.CreateCustomerRequest;
import com.example.judoStore.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {
    Customer register(CreateCustomerRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequestDto authenticationRequest);

//    AuthenticationResponseDto refresh(RefreshRequestDto refreshRequest);
//
//    void changePassword(ChangePasswordRequestDto changePasswordRequest);


    void logout(HttpServletRequest request);
    String getCurrentAccessToken(HttpServletRequest request);
}
