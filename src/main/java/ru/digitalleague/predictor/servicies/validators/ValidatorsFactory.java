package ru.digitalleague.predictor.servicies.validators;

import ru.digitalleague.predictor.interfaces.Validator;

import java.util.List;

public class ValidatorsFactory {
    public static List<Validator> getAll() {
        return List.of(
                new CommandValidatorService(),
                new CurrencyValidatorService(),
                new PeriodValidatorService()
        );
    }
}
