package ru.digitalleague.predictor.servicies.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.digitalleague.predictor.enums.Method;
import ru.digitalleague.predictor.interfaces.TelegramValidator;

import java.util.List;

@Slf4j
@Service
public class MethodValidatorService implements TelegramValidator {
    @Override
    public boolean IsAnInstance(String command) {
        log.info("Method Validator Service check command");
        try {
            return List.of(Method.values()).contains(Method.valueOf(command));
        } catch (IllegalArgumentException e) {
            log.error("Cant parse currency: " + e.getMessage());
            return false;
        }
    }
}
