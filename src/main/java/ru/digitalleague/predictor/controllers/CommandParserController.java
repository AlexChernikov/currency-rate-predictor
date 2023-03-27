package ru.digitalleague.predictor.controllers;

import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.CommandParser;

import java.util.List;

public class CommandParserController {

    private final CommandParser commandParserService;

    public CommandParserController(CommandParser commandParserService) {
        this.commandParserService = commandParserService;
    }

    public List<ValidationResult> validateCommand(String command) {
        return commandParserService.validate(command);
    }

    public Currency parseCurrency(String command) {
        return commandParserService.parseCurrency(command);
    }

    public Period parsePeriod(String command) {
        return commandParserService.parsePeriod(command);
    }

}
