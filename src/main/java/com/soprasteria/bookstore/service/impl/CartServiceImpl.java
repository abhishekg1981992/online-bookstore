package com.soprasteria.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.soprasteria.bookstore.dto.AddCartItemRequest;
import com.soprasteria.bookstore.dto.CartItemResponse;
import com.soprasteria.bookstore.dto.UpdateCartItemRequest;
import com.soprasteria.bookstore.entity.Book;
import com.soprasteria.bookstore.entity.CartItem;
import com.soprasteria.bookstore.entity.User;
import com.soprasteria.bookstore.repository.BookRepository;
import com.soprasteria.bookstore.repository.CarItemRepository;
import com.soprasteria.bookstore.repository.UserRepository;
import com.soprasteria.bookstore.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    private final CarItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public CartServiceImpl(
            CarItemRepository cartItemRepository,
            UserRepository userRepository,
            BookRepository bookRepository) {

        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<CartItemResponse> getCart() {

        User user = getCurrentUser();

        return cartItemRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void addItem(AddCartItemRequest request) {

        User user = getCurrentUser();

        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() ->
                        new RuntimeException("Book not found"));

        CartItem item = new CartItem();

        item.setUser(user);
        item.setBook(book);
        item.setQuantity(request.quantity());

        cartItemRepository.save(item);
    }

    @Override
    public void updateItem(
            Long cartItemId,
            UpdateCartItemRequest request) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException("Cart item not found"));

        item.setQuantity(request.quantity());

        cartItemRepository.save(item);
    }

    @Override
    public void removeItem(Long cartItemId) {

        cartItemRepository.deleteById(cartItemId);
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    private CartItemResponse toResponse(CartItem item) {

        BigDecimal lineTotal =
                item.getBook()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity()));

        return new CartItemResponse(
                item.getId(),
                item.getBook().getId(),
                item.getBook().getTitle(),
                item.getQuantity(),
                item.getBook().getPrice(),
                lineTotal);
    }
}