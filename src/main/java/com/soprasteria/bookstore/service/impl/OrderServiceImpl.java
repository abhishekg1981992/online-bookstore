package com.soprasteria.bookstore.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.soprasteria.bookstore.dto.OrderResponse;
import com.soprasteria.bookstore.entity.CartItem;
import com.soprasteria.bookstore.entity.Order;
import com.soprasteria.bookstore.entity.OrderItem;
import com.soprasteria.bookstore.entity.User;
import com.soprasteria.bookstore.repository.CarItemRepository;
import com.soprasteria.bookstore.repository.OrderItemRepository;
import com.soprasteria.bookstore.repository.OrderRepository;
import com.soprasteria.bookstore.repository.UserRepository;
import com.soprasteria.bookstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CarItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CarItemRepository cartItemRepository,
            UserRepository userRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderResponse checkout() {

        User user = getCurrentUser();

        List<CartItem> cartItems =
                cartItemRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        BigDecimal totalAmount = cartItems.stream()
                .map(item ->
                        item.getBook()
                                .getPrice()
                                .multiply(
                                        BigDecimal.valueOf(
                                                item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();

        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(totalAmount);

        order = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getBook().getPrice());

            orderItemRepository.save(orderItem);
        }

        cartItemRepository.deleteAll(cartItems);

        return new OrderResponse(
                order.getId(),
                order.getTotalAmount());
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}