package ru.digitalleague.predictor.servicies.validators;

import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.exceptions.UnexpectedPeriodException;
import ru.digitalleague.predictor.interfaces.Validator;

import java.util.logging.Logger;

public class PeriodValidatorService implements Validator {

    private static final Logger LOGGER = Logger.getLogger(PeriodValidatorService.class.getName());
    @Override
    public boolean validate(String command) throws UnexpectedPeriodException {
        if (!validatePeriod(command)) {
            throw new UnexpectedPeriodException("Период не найден!");
        }

        return true;
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
