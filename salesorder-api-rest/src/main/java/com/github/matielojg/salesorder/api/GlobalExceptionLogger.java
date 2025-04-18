package com.github.matielojg.salesorder.api;

import com.github.matielojg.salesorder.core.domain.exception.InvalidSalesOrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionLogger {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionLogger.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String traceId = MDC.get("traceId");

        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        logger.warn("⚠️ Validation failed [traceId={}]: {}", traceId, errors);

        String message = "Erro de validação: " + errors;
        if (traceId != null) {
            message += " (traceId: " + traceId + ")";
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception ex) {
        String traceId = MDC.get("traceId");

        logger.error("🔥 Exception occurred [traceId={}]", traceId, ex);

        String message = "Server error: " + ex.getMessage();
        if (traceId != null) {
            message += " (traceId: " + traceId + ")";
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }

    @ExceptionHandler(InvalidSalesOrderException.class)
    public ResponseEntity<String> handleInvalidSalesOrder(InvalidSalesOrderException ex) {
        String traceId = MDC.get("traceId");
        logger.warn("❌ Invalid sales order [traceId={}]: {}", traceId, ex.getMessage());

        String message = "Erro de negócio: " + ex.getMessage();
        if (traceId != null) {
            message += " (traceId: " + traceId + ")";
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }
}
