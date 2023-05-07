package io.github.guilhermeabroncari.adopetapi.infra;

import io.github.guilhermeabroncari.adopetapi.domain.exception.DomainNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdopetExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequest(MethodArgumentNotValidException ex) {
        var error = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(ExceptionValidation::new).toList());
    }
    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity notFoundByClientSide(DomainNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record ExceptionValidation(String field, String message) {
        public ExceptionValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
