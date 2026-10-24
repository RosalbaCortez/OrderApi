package com.tecylab.order_api.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        String description,
        BigDecimal cost
) {
}
