package com.ecommerce.backend.dto.response;

import com.ecommerce.backend.dto.CategoryDto;

public record ProductResponse(
        Long id,
        String name,
        String description,
        String price,
        CategoryDto category
) { }
