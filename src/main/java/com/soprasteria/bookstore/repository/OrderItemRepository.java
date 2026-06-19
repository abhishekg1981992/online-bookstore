package com.soprasteria.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.bookstore.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
