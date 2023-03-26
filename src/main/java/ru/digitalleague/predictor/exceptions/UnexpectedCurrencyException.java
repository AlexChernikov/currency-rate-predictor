package ru.digitalleague.predictor.exceptions;

import lombok.Getter;

@Getter
public class UnexpectedCurrencyException extends RuntimeException {
    private final String detailMessage;
    public UnexpectedCurrencyException(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
