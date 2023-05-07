package io.github.guilhermeabroncari.adopetapi.domain.exception;

public class DomainNotFoundException extends RuntimeException {
    public DomainNotFoundException(String message) {
        super(message);
    }
}
