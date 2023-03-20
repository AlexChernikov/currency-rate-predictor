package ru.digitalleague.philosophyit.controllers;

import ru.digitalleague.philosophyit.enums.Currency;
import ru.digitalleague.philosophyit.enums.Period;
import ru.digitalleague.philosophyit.servicies.CommandParserService;

public class CommandParserController {

    CommandParserService commandParserService = new CommandParserService();

    public boolean validateCommand(String command) {
        return commandParserService.validate(command);
    }

    public Currency parseCurrency(String command) {
        return commandParserService.parseCurrency(command);
    }

    public Period parsePeriod(String command) {
        return commandParserService.parsePeriod(command);
    }

}
