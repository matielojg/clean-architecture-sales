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

    private static final String TRACE_ID = "traceId";
    private static final String VALIDATION_ERROR_PREFIX = "Erro de validação: ";
    private static final String BUSINESS_ERROR_PREFIX = "Erro de negócio: ";
    private static final String SERVER_ERROR_PREFIX = "Server error: ";

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionLogger.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String traceId = MDC.get(TRACE_ID);

        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        logger.warn("⚠️ Validation failed [traceId={}]: {}", traceId, errors);

        String message = appendTraceId(VALIDATION_ERROR_PREFIX + errors, traceId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception ex) {
        String traceId = MDC.get(TRACE_ID);

        logger.error("🔥 Exception occurred [traceId={}]", traceId, ex);

        String message = appendTraceId(SERVER_ERROR_PREFIX + ex.getMessage(), traceId);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(InvalidSalesOrderException.class)
    public ResponseEntity<String> handleInvalidSalesOrder(InvalidSalesOrderException ex) {
        String traceId = MDC.get(TRACE_ID);
        logger.warn("❌ Invalid sales order [traceId={}]: {}", traceId, ex.getMessage());

        String message = appendTraceId(BUSINESS_ERROR_PREFIX + ex.getMessage(), traceId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    private String appendTraceId(String message, String traceId) {
        return (traceId != null) ? message + " (traceId: " + traceId + ")" : message;
    }
}
