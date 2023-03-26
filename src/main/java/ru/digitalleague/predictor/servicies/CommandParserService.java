package ru.digitalleague.predictor.servicies;

import lombok.extern.slf4j.Slf4j;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.exceptions.CommandFormatException;
import ru.digitalleague.predictor.exceptions.UnexpectedCurrencyException;
import ru.digitalleague.predictor.exceptions.UnexpectedPeriodException;
import ru.digitalleague.predictor.interfaces.Validator;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class CommandParserService {

    private final List<Validator> validators;

    public CommandParserService(Validator... validators) {
        this.validators = Arrays.stream(validators).toList();
    }


    public boolean validate(String command) {
        try {
            return validators.stream()
                    .allMatch(validator -> validator.validate(command));
        } catch (CommandFormatException e) {
            log.error(e.getDetailMessage());
            return false;
        } catch (UnexpectedCurrencyException e) {
            log.error(e.getDetailMessage());
            return false;
        } catch (UnexpectedPeriodException e) {
            log.error(e.getDetailMessage());
            return false;
        }
    }

    public Currency parseCurrency(String command) throws CommandFormatException {
        for (Currency value : Currency.values()) {
            if (command.contains(value.toString())) {
                return value;
            }
        }

        throw new CommandFormatException("Валюта не найдена!");
    }

    public Period parsePeriod(String command) throws CommandFormatException {
        for (Period value : Period.values()) {
            if (command.contains(value.toString())) {
                return value;
            }
        }

        throw new CommandFormatException("Период не найден!");
    }
}
