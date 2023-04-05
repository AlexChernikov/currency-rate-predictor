package ru.digitalleague.predictor.servicies.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.interfaces.TelegramValidator;
import ru.digitalleague.predictor.interfaces.Validator;

import java.util.List;

@Slf4j
@Service
public class CurrencyValidatorService implements Validator, TelegramValidator {
    @Override
    public ValidationResult isValid(String command){
        if (!validateCurrency(command)) {
            return new ValidationResult(false, command + " Валюта не найдена!");
        }

        return new ValidationResult(true, "Ok!");
    }

    @Override
    public boolean IsAnInstance(String command) {
        log.info("Currency Validator Service check command");
        try {
            return List.of(Currency.values()).contains(Currency.valueOf(command));
        } catch (IllegalArgumentException e) {
            log.warn("Cant parse currency: " + e.getMessage());
            return false;
        }
    }

    public boolean validateCurrency(String command) {
        for (Currency value : Currency.values()) {
            if (command.matches("(.*) " + value.toString() + " (.*)")) {
                return true;
            }
        }

        return false;
    }
}
