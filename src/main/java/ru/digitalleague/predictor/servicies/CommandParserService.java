package ru.digitalleague.predictor.servicies;

import lombok.extern.slf4j.Slf4j;
import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.exceptions.CommandFormatException;
import ru.digitalleague.predictor.exceptions.UnexpectedCurrencyException;
import ru.digitalleague.predictor.exceptions.UnexpectedPeriodException;
import ru.digitalleague.predictor.interfaces.Validator;

import java.util.List;

@Slf4j
public class CommandParserService {

    private final List<Validator> validators;

    public CommandParserService(List<Validator> validators) {
        this.validators = validators;
    }


    public ValidationResult validate(String command) {
        ValidationResult validationResult = new ValidationResult();
        try {
            boolean isValid = validators.stream()
                    .allMatch(validator -> validator.validate(command));
            validationResult.setValid(isValid);
        } catch (CommandFormatException e) {
            validationResult.setDetailMessage(e.getDetailMessage());
        } catch (UnexpectedCurrencyException e) {
            validationResult.setDetailMessage(e.getDetailMessage());
        } catch (UnexpectedPeriodException e) {
            validationResult.setDetailMessage(e.getDetailMessage());
        } finally{
            return validationResult;
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
