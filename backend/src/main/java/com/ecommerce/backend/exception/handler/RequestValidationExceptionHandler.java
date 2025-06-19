package com.ecommerce.backend.exception.handler;

import com.ecommerce.backend.exception.ErrorResponse;
import com.ecommerce.backend.exception.RequestValidatorErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RequestValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestValidatorErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> messages = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName;
            try {
                fieldName = ((FieldError) err).getField();
            } catch (ClassCastException exception) {
                fieldName = err.getObjectName();
            }

            messages.add(fieldName + ": " + err.getDefaultMessage());
        });

        return new ResponseEntity<>(
                new RequestValidatorErrorResponse(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), messages),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RequestValidatorErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(
                new RequestValidatorErrorResponse(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), List.of(ex.getMessage())),
                HttpStatus.BAD_REQUEST
        );
    }
}
