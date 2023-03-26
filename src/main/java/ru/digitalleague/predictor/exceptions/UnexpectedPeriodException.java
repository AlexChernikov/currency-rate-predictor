package ru.digitalleague.predictor.exceptions;

import lombok.Getter;

@Getter
public class UnexpectedPeriodException extends RuntimeException {
    private final String detailMessage;
    public UnexpectedPeriodException(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
