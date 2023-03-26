package ru.digitalleague.predictor.exceptions;

import lombok.Getter;

@Getter
public class CommandFormatException extends RuntimeException {
    private final String detailMessage;
    public CommandFormatException(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
