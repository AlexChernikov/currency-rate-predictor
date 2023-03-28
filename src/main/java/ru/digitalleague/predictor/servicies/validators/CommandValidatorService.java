package ru.digitalleague.predictor.servicies.validators;

import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.interfaces.Validator;

public class CommandValidatorService implements Validator {
    @Override
    public ValidationResult isValid(String command) {
        if (!command.matches("rate [A-Z]{3} (.*)")) {
            return new ValidationResult(false, "Команда " + command + " не разбирается. Попробуйте ещё раз.");
        }

        return new ValidationResult(true, "Ok!");
    }
}
