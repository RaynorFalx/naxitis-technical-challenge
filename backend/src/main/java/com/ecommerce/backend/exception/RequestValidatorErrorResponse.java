package com.ecommerce.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestValidatorErrorResponse {
    private int statusCode;
    private LocalDateTime timestamp;
    private List<String> messages;
}
