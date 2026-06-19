package com.soprasteria.bookstore.service;

import java.util.List;

import com.soprasteria.bookstore.dto.AddCartItemRequest;
import com.soprasteria.bookstore.dto.CartItemResponse;
import com.soprasteria.bookstore.dto.UpdateCartItemRequest;

public interface CartService {

    List<CartItemResponse> getCart();

    void addItem(AddCartItemRequest request);

    void updateItem(Long cartItemId,
                    UpdateCartItemRequest request);

    void removeItem(Long cartItemId);
}