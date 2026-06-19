package com.soprasteria.bookstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.soprasteria.bookstore.dto.AddCartItemRequest;
import com.soprasteria.bookstore.dto.CartItemResponse;
import com.soprasteria.bookstore.dto.UpdateCartItemRequest;
import com.soprasteria.bookstore.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(
            CartService cartService) {

        this.cartService = cartService;
    }

    @GetMapping
    public List<CartItemResponse> getCart() {
        return cartService.getCart();
    }

    @PostMapping("/items")
    public void addItem(
            @Valid @RequestBody AddCartItemRequest request) {

        cartService.addItem(request);
    }

    @PutMapping("/items/{id}")
    public void updateItem(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCartItemRequest request) {

        cartService.updateItem(id, request);
    }

    @DeleteMapping("/items/{id}")
    public void removeItem(
            @PathVariable Long id) {

        cartService.removeItem(id);
    }
}