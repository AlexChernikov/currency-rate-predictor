package ru.digitalleague.predictor.servicies.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.digitalleague.predictor.enums.Format;
import ru.digitalleague.predictor.interfaces.TelegramValidator;

import java.util.List;

@Slf4j
@Service
public class FormatValidatorService implements TelegramValidator {
    @Override
    public boolean IsAnInstance(String command) {
        log.info("Format Validator Service check command");
        try {
            return List.of(Format.values()).contains(Format.valueOf(command));
        } catch (IllegalArgumentException e) {
            log.warn("Cant parse currency: " + e.getMessage());
            return false;
        }
    }
}
