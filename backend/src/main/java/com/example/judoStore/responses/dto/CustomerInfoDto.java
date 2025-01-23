package com.example.judoStore.responses.dto;

public record CustomerInfoDto(
        String name,
        String email,
        String city,
        String phoneNumber
) {
}
