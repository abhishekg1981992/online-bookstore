package com.soprasteria.bookstore.dto;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderId,
        BigDecimal totalAmount
) {
}