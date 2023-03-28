package ru.digitalleague.predictor.servicies.validators;

import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.interfaces.Validator;

public class CurrencyValidatorService implements Validator {
    @Override
    public ValidationResult isValid(String command){
        if (!validateCurrency(command)) {
            return new ValidationResult(false, command + " Валюта не найдена!");
        }

        return new ValidationResult(true, "Ok!");
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
