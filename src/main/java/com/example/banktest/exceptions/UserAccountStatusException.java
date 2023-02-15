package com.example.banktest.exceptions;

public class UserAccountStatusException extends RuntimeException {

    public UserAccountStatusException(String message) {
        super(message);
    }

    public UserAccountStatusException() {
        super("The user account status is not suitable for this operation");
    }
}