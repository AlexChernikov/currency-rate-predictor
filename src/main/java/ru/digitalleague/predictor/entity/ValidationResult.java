package ru.digitalleague.predictor.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationResult {
    private String detailMessage = null;
    private boolean isValid = false;
}
