package com.example.banktest.exceptions;

public class UserAccountBalanceException extends RuntimeException {

    public UserAccountBalanceException(String message) {
        super(message);
    }

    public UserAccountBalanceException() {
        super("User account balance does not match this operation");
    }
}
