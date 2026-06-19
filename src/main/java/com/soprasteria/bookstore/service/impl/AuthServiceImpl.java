package com.soprasteria.bookstore.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soprasteria.bookstore.dto.AuthResponse;
import com.soprasteria.bookstore.dto.LoginRequest;
import com.soprasteria.bookstore.dto.RegisterRequest;
import com.soprasteria.bookstore.entity.User;
import com.soprasteria.bookstore.repository.UserRepository;
import com.soprasteria.bookstore.security.JwtService;
import com.soprasteria.bookstore.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public void register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(
                passwordEncoder.encode(request.password()));

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() ->
                        new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword())) {

            throw new RuntimeException("Invalid credentials");
        }

        String token =
                jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}