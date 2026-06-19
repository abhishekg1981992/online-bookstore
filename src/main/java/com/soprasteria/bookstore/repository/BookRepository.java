package com.soprasteria.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.bookstore.entity.Book;
import com.soprasteria.bookstore.entity.User;

public interface BookRepository extends JpaRepository<Book, Long> {


}
