package ru.digitalleague.predictor.servicies.validators;

import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.Validator;

import java.util.logging.Logger;

public class PeriodValidatorService implements Validator {

    private static final Logger LOGGER = Logger.getLogger(PeriodValidatorService.class.getName());
    @Override
    public ValidationResult isValid(String command) {
        if (!validatePeriod(command)) {
            return new ValidationResult(false, command + " Период не найден!");
        }

        return new ValidationResult(true, "Ok!");
    }

    public boolean validatePeriod(String command) {
        for (Period value : Period.values()) {
            if (command.matches("(.*) " + value.toString())) {
                return true;
            }
        }

        return false;
    }
}
