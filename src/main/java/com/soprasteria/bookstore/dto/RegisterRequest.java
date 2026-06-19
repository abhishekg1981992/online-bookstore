package com.soprasteria.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(

        @NotBlank
        String username,

        @NotBlank
        String password

) {}