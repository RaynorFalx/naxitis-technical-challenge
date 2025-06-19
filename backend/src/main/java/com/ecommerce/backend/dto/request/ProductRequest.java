package com.ecommerce.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotBlank(message = "Product NAME cannot be blank")
        String name,
        @NotBlank(message = "Product DESCRIPTION cannot be blank")
        String description,
        @NotBlank(message = "Product PRICE cannot be blank")
        String price,
        @NotNull(message = "Category ID cannot be null")
        Long categoryId
) {}
