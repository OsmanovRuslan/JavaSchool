package org.example.exception;

public class UnauthorizedAccessException extends Exception {

    public UnauthorizedAccessException(String description) {
        super(description);
    }

}
