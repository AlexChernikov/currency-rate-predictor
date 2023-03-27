package ru.digitalleague.predictor.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationResult {
    private final boolean isValid;
    private final String detailMessage;

    public ValidationResult(boolean isValid, String detailMessage) {
        this.isValid = isValid;
        this.detailMessage = detailMessage;
    }
}
