package com.soprasteria.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.bookstore.entity.Book;
import com.soprasteria.bookstore.entity.CartItem;
import com.soprasteria.bookstore.entity.Order;
import com.soprasteria.bookstore.entity.OrderItem;
import com.soprasteria.bookstore.entity.User;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
