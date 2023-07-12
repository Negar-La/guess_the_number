package com.sg.GuessMyNumber.service;

public class GameDataValidationException extends Exception {
    public GameDataValidationException(String message) {
        super(message);
    }

    public GameDataValidationException (String message, Throwable cause) {
        super(message, cause);
    }
}
