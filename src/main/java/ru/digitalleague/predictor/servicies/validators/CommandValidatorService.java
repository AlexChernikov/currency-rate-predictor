package ru.digitalleague.predictor.servicies.validators;

import ru.digitalleague.predictor.exceptions.CommandFormatException;
import ru.digitalleague.predictor.interfaces.Validator;

public class CommandValidatorService implements Validator {
    @Override
    public boolean validate(String command) throws CommandFormatException {
        if (!command.matches("rate [A-Z]{3} (.*)")) {
            throw new CommandFormatException("Команда " + command + " не разбирается. Попробуйте ещё раз.");
        }

        return true;
    }
}
