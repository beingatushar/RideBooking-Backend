package com.beingatushar.ubercommons.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles custom ResourceNotFoundException.
     * Triggered when a requested resource (e.g., a specific user or ride) is not found in the database.
     *
     * @param ex      The thrown ResourceNotFoundException.
     * @param request The current web request.
     * @return A ResponseEntity with a 404 NOT_FOUND status and a structured error message.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                Instant.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles validation errors from @Valid annotation.
     * Triggered when a request body fails validation checks (e.g., a required field is null).
     *
     * @param ex      The thrown MethodArgumentNotValidException.
     * @param request The current web request.
     * @return A ResponseEntity with a 400 BAD_REQUEST status and details of the validation failure.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(
                "Validation failed: " + errorMessage,
                HttpStatus.BAD_REQUEST,
                Instant.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles database constraint violation exceptions.
     * Triggered by violations of unique constraints (e.g., duplicate email) or foreign key constraints.
     *
     * @param ex      The thrown DataIntegrityViolationException.
     * @param request The current web request.
     * @return A ResponseEntity with a 409 CONFLICT status.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Database constraint violation. A resource may already exist or a required reference is missing.",
                HttpStatus.CONFLICT,
                Instant.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles all other un-caught exceptions.
     * This acts as a fallback for any unexpected errors.
     *
     * @param ex      The thrown Exception.
     * @param request The current web request.
     * @return A ResponseEntity with a 500 INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                Instant.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}