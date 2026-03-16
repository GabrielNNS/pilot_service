package com.gns.pilot_service.infra.handlers;

import feign.FeignException;
import feign.RetryableException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse buildErrorResponse(Integer errorCode, String errorMessage) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        ErrorResponse error = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {

        ErrorResponse error = buildErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ErrorResponse> handleRetryableException(RetryableException ex) {

        ErrorResponse error = buildErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleFeignNotFoundException(FeignException.NotFound ex) {

        ErrorResponse error = buildErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
