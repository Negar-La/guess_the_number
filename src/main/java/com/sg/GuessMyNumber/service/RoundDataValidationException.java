package com.sg.GuessMyNumber.service;

public class RoundDataValidationException extends Exception {
    public RoundDataValidationException(String message) {
        super(message);
    }

    public RoundDataValidationException (String message, Throwable cause) {
        super(message, cause);
    }
}
