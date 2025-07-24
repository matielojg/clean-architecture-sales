package com.github.matielojg.salesorder.api.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manipulador de exceções específico para testes que captura NullPointerException
 * e retorna um status 400 BAD_REQUEST para simular falhas de validação.
 */
@ControllerAdvice
@Profile("test")
public class TestValidationExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Validation error: " + ex.getMessage());
    }
}