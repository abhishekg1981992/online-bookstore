package com.soprasteria.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.bookstore.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
