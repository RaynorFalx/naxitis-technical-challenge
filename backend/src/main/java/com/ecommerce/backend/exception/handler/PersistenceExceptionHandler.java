package com.ecommerce.backend.exception.handler;

import com.ecommerce.backend.exception.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class PersistenceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException exception) {
        return new ResponseEntity<>(makeErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    private ErrorResponse makeErrorResponse(String message) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), message);
    }
}
