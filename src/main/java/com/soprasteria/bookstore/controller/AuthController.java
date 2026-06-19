package com.soprasteria.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.soprasteria.bookstore.dto.AuthResponse;
import com.soprasteria.bookstore.dto.LoginRequest;
import com.soprasteria.bookstore.dto.RegisterRequest;
import com.soprasteria.bookstore.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}