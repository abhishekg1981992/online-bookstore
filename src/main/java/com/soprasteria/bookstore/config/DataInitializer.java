package com.soprasteria.bookstore.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.soprasteria.bookstore.entity.Book;
import com.soprasteria.bookstore.repository.BookRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {

        if (bookRepository.count() > 0) {
            return;
        }

        bookRepository.saveAll(List.of(

                createBook(
                        "Clean Code",
                        "Robert C. Martin",
                        25),

                createBook(
                        "Effective Java",
                        "Joshua Bloch",
                        30),

                createBook(
                        "Spring in Action",
                        "Craig Walls",
                        35),

                createBook(
                        "Java Concurrency in Practice",
                        "Brian Goetz",
                        40),

                createBook(
                        "Refactoring",
                        "Martin Fowler",
                        32),

                createBook(
                        "Design Patterns",
                        "GoF",
                        45),

                createBook(
                        "Head First Design Patterns",
                        "Eric Freeman",
                        28),

                createBook(
                        "Microservices Patterns",
                        "Chris Richardson",
                        38),

                createBook(
                        "Domain Driven Design",
                        "Eric Evans",
                        50),

                createBook(
                        "The Pragmatic Programmer",
                        "Andrew Hunt",
                        27)
        ));
    }

    private Book createBook(
            String title,
            String author,
            double price) {

        Book book = new Book();

        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(BigDecimal.valueOf(price));

        return book;
    }
}