package ru.digitalleague.predictor.interfaces;

import ru.digitalleague.predictor.entity.ValidationResult;

public interface Validator {
    ValidationResult validate(String command);
}