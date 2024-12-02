package org.example.exception;

public class InvalidAmountArgumentException extends Exception {

    public InvalidAmountArgumentException(String description) {
        super(description);
    }

}
