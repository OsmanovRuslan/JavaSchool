package org.example.exception;

public class AccountIsLockedException extends Exception {

    public AccountIsLockedException(String description) {
        super(description);
    }

}
