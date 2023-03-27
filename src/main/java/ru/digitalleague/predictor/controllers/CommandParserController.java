package ru.digitalleague.predictor.controllers;

import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.CommandParserService;

public class CommandParserController {

    private final CommandParserService commandParserService;

    public CommandParserController(CommandParserService commandParserService) {
        this.commandParserService = commandParserService;
    }

    public ValidationResult validateCommand(String command) {
        return commandParserService.validate(command);
    }

    public Currency parseCurrency(String command) {
        return commandParserService.parseCurrency(command);
    }

    public Period parsePeriod(String command) {
        return commandParserService.parsePeriod(command);
    }

}
