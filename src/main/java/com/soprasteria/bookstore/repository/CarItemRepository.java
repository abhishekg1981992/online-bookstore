package com.soprasteria.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.bookstore.entity.Book;
import com.soprasteria.bookstore.entity.CartItem;
import com.soprasteria.bookstore.entity.User;

public interface CarItemRepository extends JpaRepository<CartItem, Long> {

	   List<CartItem> findByUser(User user);

}
