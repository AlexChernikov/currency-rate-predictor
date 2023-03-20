package ru.digitalleague.philosophyit.servicies.validators;

import ru.digitalleague.philosophyit.enums.Period;
import ru.digitalleague.philosophyit.interfaces.Validator;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeriodValidatorService implements Validator {

    private static final Logger LOGGER = Logger.getLogger(PeriodValidatorService.class.getName());
    @Override
    public boolean validate(String command) {
        if (!validatePeriod(command)) {
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.stream(Period.values()).forEach(v -> stringBuilder.append(" ").append(v));

            LOGGER.log(Level.WARNING, "Период не найден!");
            LOGGER.log(Level.INFO, "Список доступных для прогноза периодов:{0}", stringBuilder);

            return false;
        }

        return true;
    }

    public boolean validatePeriod(String command) {
        for (Period value : Period.values()) {
            if (command.matches("(.*) " + value.toString())) {
                return true;
            }
        }

        return false;
    }
}
