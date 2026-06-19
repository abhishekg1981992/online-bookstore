package com.soprasteria.bookstore.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soprasteria.bookstore.entity.Book;
import com.soprasteria.bookstore.repository.BookRepository;
import com.soprasteria.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}