package ru.digitalleague.predictor.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.servicies.CommandParser;
import ru.digitalleague.predictor.servicies.CurrencyPredictorService;
import ru.digitalleague.predictor.servicies.validators.ValidatorsFactory;

import java.util.Arrays;
import java.util.List;

class CurrencyPredictorControllerTest {

    @Test
    void correctDataTest() {
        List<String> data = Arrays.asList("rate TRY tomorrow", "rate USD week");
        CommandParserController commandParserController = new CommandParserController(new CommandParser(ValidatorsFactory.getAll()));
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(new CurrencyPredictorService());

        data.stream()
                .filter(command -> commandParserController.validateCommand(command).stream().allMatch(ValidationResult::isValid))
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void badDataTest() {
        List<String> data = Arrays.asList("rate TRY", "USD week");
        CommandParserController commandParserController = new CommandParserController(new CommandParser(ValidatorsFactory.getAll()));
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(new CurrencyPredictorService());

        data.stream()
                .filter(command -> commandParserController.validateCommand(command).stream().allMatch(ValidationResult::isValid))
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNull);
    }

    @Test
    void badCurrencyTest() {
        List<String> data = Arrays.asList("rate TTT tomorrow", "rate UUU week");
        CommandParserController commandParserController = new CommandParserController(new CommandParser(ValidatorsFactory.getAll()));
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(new CurrencyPredictorService());

        data.stream()
                .filter(command -> commandParserController.validateCommand(command).stream().allMatch(ValidationResult::isValid))
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNull);
    }

    @Test
    void badPeriodTest() {
        List<String> data = Arrays.asList("rate TRY yesterday", "rate USD month");
        CommandParserController commandParserController = new CommandParserController(new CommandParser(ValidatorsFactory.getAll()));
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(new CurrencyPredictorService());

        data.stream()
                .filter(command -> commandParserController.validateCommand(command).stream().allMatch(ValidationResult::isValid))
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNull);
    }
}
