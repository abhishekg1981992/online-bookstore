package com.soprasteria.bookstore.service;

import com.soprasteria.bookstore.dto.AuthResponse;
import com.soprasteria.bookstore.dto.LoginRequest;
import com.soprasteria.bookstore.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}