package com.gabriel.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import java.math.BigDecimal;
@Builder
public record ProductRequestDTO(
        @NotBlank
        String name,
        @NotNull
        @Positive
        BigDecimal price,
        @NotNull
        @PositiveOrZero
        Integer stock) {
}

