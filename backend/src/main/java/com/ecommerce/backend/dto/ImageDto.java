package com.ecommerce.backend.dto;

public record ImageDto(
        String filePath,
        String contentType,
        boolean primary
) {}
