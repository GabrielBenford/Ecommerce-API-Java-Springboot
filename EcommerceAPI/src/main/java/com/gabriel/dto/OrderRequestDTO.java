package com.gabriel.dto;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record OrderRequestDTO(
        @NotEmpty
        Set<OrderItemRequestDTO> orderItems
) {
}

