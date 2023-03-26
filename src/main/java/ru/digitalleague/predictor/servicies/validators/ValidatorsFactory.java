package ru.digitalleague.predictor.servicies.validators;

import ru.digitalleague.predictor.interfaces.Validator;

public class ValidatorsFactory {
    public static Validator[] getAll() {
        return new Validator[] {
                new CommandValidatorService(),
                new CurrencyValidatorService(),
                new PeriodValidatorService()
        };
    }
}
