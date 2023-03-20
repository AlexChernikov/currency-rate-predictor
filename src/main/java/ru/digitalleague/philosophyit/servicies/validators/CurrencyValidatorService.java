package ru.digitalleague.philosophyit.servicies.validators;

import ru.digitalleague.philosophyit.enums.Currency;
import ru.digitalleague.philosophyit.interfaces.Validator;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyValidatorService implements Validator {

    private static final Logger LOGGER = Logger.getLogger(CurrencyValidatorService.class.getName());
    @Override
    public boolean validate(String command) {
        if (!validateCurrency(command)) {
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.stream(Currency.values()).forEach(v -> stringBuilder.append(" " + v));

            LOGGER.log(Level.WARNING, "Валюта не найдена!");
            LOGGER.log(Level.INFO, "Список доступных для прогноза валют:{0}", stringBuilder);

            return false;
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
