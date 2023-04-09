package ru.digitalleague.predictor.exceptions;

public class CurrencyFileNotFoundException extends RuntimeException {
    public CurrencyFileNotFoundException(String message) {
        super(message);
    }
}
