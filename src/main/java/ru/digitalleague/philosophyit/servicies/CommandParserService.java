package ru.digitalleague.philosophyit.servicies;

import ru.digitalleague.philosophyit.enums.Currency;
import ru.digitalleague.philosophyit.enums.Period;
import ru.digitalleague.philosophyit.interfaces.Validator;
import ru.digitalleague.philosophyit.servicies.validators.CommandValidatorService;
import ru.digitalleague.philosophyit.servicies.validators.CurrencyValidatorService;
import ru.digitalleague.philosophyit.servicies.validators.PeriodValidatorService;

public class CommandParserService {

    Validator commandValidatorService = new CommandValidatorService();
    Validator currencyValidatorService = new CurrencyValidatorService();
    Validator periodValidatorService = new PeriodValidatorService();

    public boolean validate(String command) {
        return commandValidatorService.validate(command)
                && currencyValidatorService.validate(command)
                && periodValidatorService.validate(command);
    }

    public Currency parseCurrency(String command) {
        for (Currency value : Currency.values()) {
            if (command.contains(value.toString())) {
                return value;
            }
        }

        return null;
    }

    public Period parsePeriod(String command) {
        for (Period value : Period.values()) {
            if (command.contains(value.toString())) {
                return value;
            }
        }

        return null;
    }
}
