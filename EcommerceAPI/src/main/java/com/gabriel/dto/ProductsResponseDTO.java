package com.gabriel.dto;

import java.math.BigDecimal;

public record ProductsResponseDTO(
        Long id,
        String name,
        BigDecimal price,
        Integer stock)  {
}

