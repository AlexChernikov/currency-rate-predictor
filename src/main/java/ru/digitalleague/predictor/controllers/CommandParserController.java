package ru.digitalleague.predictor.controllers;

import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.exceptions.CommandFormatException;
import ru.digitalleague.predictor.servicies.CommandParserService;
import ru.digitalleague.predictor.servicies.validators.ValidatorsFactory;

public class CommandParserController {

    private final CommandParserService commandParserService;

    public CommandParserController() {
        commandParserService = new CommandParserService(ValidatorsFactory.getAll());
    }

    public boolean validateCommand(String command) {
        return commandParserService.validate(command);
    }

    public Currency parseCurrency(String command) throws CommandFormatException {
        return commandParserService.parseCurrency(command);
    }

    public Period parsePeriod(String command) throws CommandFormatException {
        return commandParserService.parsePeriod(command);
    }

}
