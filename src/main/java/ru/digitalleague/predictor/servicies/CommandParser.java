package ru.digitalleague.predictor.servicies;

import lombok.extern.slf4j.Slf4j;
import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.Validator;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CommandParser {

    private final List<Validator> validators;

    public CommandParser(List<Validator> validators) {
        this.validators = validators;
    }

    public boolean isValidCommand(String command) {
        return validate(command).stream()
                .allMatch(ValidationResult::isValid);
    }

    public String getDetailLogMessage(String command) {
        StringBuilder stringBuilder = new StringBuilder();

        validate(command).stream()
                .map(ValidationResult::getDetailMessage)
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }


    public List<ValidationResult> validate(String command) {
        return  validators.stream()
                .map(validator -> validator.isValid(command))
                .collect(Collectors.toList());
    }

    public Currency parseCurrency(String command) {
        for (Currency value : Currency.values()) {
            if (command.contains(value.toString())) {
                return value;
            }
        }

        throw new RuntimeException(command + " Неизвестная валюта!");
    }

    public Period parsePeriod(String command) {
        for (Period value : Period.values()) {
            if (command.contains(value.toString())) {
                return value;
            }
        }

        throw new RuntimeException(command + " Неизвестный период!");
    }
}
