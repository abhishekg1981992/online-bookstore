package com.soprasteria.bookstore.dto;

import java.math.BigDecimal;

public record CartItemResponse(
        Long id,
        Long bookId,
        String title,
        Integer quantity,
        BigDecimal price,
        BigDecimal lineTotal
) {
}