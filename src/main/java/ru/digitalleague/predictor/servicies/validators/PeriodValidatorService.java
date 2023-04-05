package ru.digitalleague.predictor.servicies.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.TelegramValidator;
import ru.digitalleague.predictor.interfaces.Validator;

import java.util.List;

@Slf4j
@Service
public class PeriodValidatorService implements Validator, TelegramValidator {
    @Override
    public ValidationResult isValid(String command) {
        if (!validatePeriod(command)) {
            return new ValidationResult(false, command + " Период не найден!");
        }

        return new ValidationResult(true, "Ok!");
    }

    @Override
    public boolean IsAnInstance(String command) {
        log.info("Period Validator Service check command");
        try {
            return List.of(Period.values()).contains(Period.valueOf(command));
        } catch (IllegalArgumentException e) {
            log.warn("Cant parse currency: " + e.getMessage());
            return false;
        }
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
