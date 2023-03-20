package ru.digitalleague.philosophyit.servicies.validators;

import ru.digitalleague.philosophyit.interfaces.Validator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandValidatorService implements Validator {

    private static final Logger LOGGER = Logger.getLogger(CommandValidatorService.class.getName());
    @Override
    public boolean validate(String command) {
        if (!command.matches("rate [A-Z]{3} (.*)")) {
            LOGGER.log(Level.WARNING, "Команда не разбирается. Попробуйте ещё раз.");

            return false;
        }
        return true;
    }
}
