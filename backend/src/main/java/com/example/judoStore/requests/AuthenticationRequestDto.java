package com.example.judoStore.requests;

import lombok.Builder;

@Builder
public record AuthenticationRequestDto (

        String login,

        String password

) {
}