package ru.digitalleague.predictor.servicies.validators;

import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.exceptions.UnexpectedCurrencyException;
import ru.digitalleague.predictor.interfaces.Validator;

public class CurrencyValidatorService implements Validator {
    @Override
    public boolean validate(String command) throws UnexpectedCurrencyException {
        if (!validateCurrency(command)) {
            throw new UnexpectedCurrencyException("Валюта не найдена!");
        }

        return true;
    }

    public boolean validateCurrency(String command) {
        for (Currency value : Currency.values()) {
            if (command.matches("(.*) " + value.toString() + " (.*)")) {
                return true;
            }
        }

        return false;
    }
}
