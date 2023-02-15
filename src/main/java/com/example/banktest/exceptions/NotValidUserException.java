package com.example.banktest.exceptions;

public class NotValidUserException extends RuntimeException {

    public NotValidUserException(String message) {
        super(message);
    }

    public NotValidUserException() {
        super("User not valid");
    }
}