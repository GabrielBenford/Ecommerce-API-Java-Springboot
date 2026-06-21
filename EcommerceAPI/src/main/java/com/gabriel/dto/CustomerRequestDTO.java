package com.gabriel.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CustomerRequestDTO(
                                 @NotBlank
                                 String name,
                                 @NotBlank
                                 @Email
                                 String email,
                                 @NotBlank
                                 String confirmPassword,
                                 @NotBlank
                                 String password) {
}

