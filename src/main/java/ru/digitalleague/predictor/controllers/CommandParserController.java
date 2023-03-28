package ru.digitalleague.predictor.controllers;

import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.CommandParser;

public class CommandParserController {

    private final CommandParser commandParserService;

    public CommandParserController(CommandParser commandParserService) {
        this.commandParserService = commandParserService;
    }

    public boolean isValidCommand(String command) {
        return commandParserService.isValidCommand(command);
    }

    public String getDetailLogMessage(String command) {
        return commandParserService.getDetailLogMessage(command);
    }

    public Currency parseCurrency(String command) {
        return commandParserService.parseCurrency(command);
    }

    public Period parsePeriod(String command) {
        return commandParserService.parsePeriod(command);
    }
}
