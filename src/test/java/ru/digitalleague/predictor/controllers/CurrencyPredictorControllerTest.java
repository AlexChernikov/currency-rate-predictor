package ru.digitalleague.predictor.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.digitalleague.predictor.servicies.CommandParser;
import ru.digitalleague.predictor.servicies.CurrencyPredictor;
import ru.digitalleague.predictor.servicies.repository.CurrencyInfoRepository;
import ru.digitalleague.predictor.servicies.validators.ValidatorsFactory;

import java.util.Arrays;
import java.util.List;

class CurrencyPredictorControllerTest {

    @Test
    void correctDataTest() {
        List<String> data = Arrays.asList("rate TRY tomorrow", "rate USD week");
        CommandParser commandParser = new CommandParser(ValidatorsFactory.getAll());
        CommandParserController commandParserController = new CommandParserController(commandParser);

        CurrencyPredictor currencyPredictor = new CurrencyPredictor(new CurrencyInfoRepository());
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(currencyPredictor);

        data.stream()
                .filter(command -> commandParserController.isValidCommand(command))
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void badDataTest() {
        List<String> data = Arrays.asList("rate TRY", "USD week");
        CommandParser commandParser = new CommandParser(ValidatorsFactory.getAll());
        CommandParserController commandParserController = new CommandParserController(commandParser);

        CurrencyPredictor currencyPredictor = new CurrencyPredictor(new CurrencyInfoRepository());
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(currencyPredictor);

        data.stream()
                .filter(command -> commandParserController.isValidCommand(command))
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNull);
    }

    @Test
    void badCurrencyTest() {
        List<String> data = Arrays.asList("rate TTT tomorrow", "rate UUU week");
        CommandParser commandParser = new CommandParser(ValidatorsFactory.getAll());
        CommandParserController commandParserController = new CommandParserController(commandParser);

        CurrencyPredictor currencyPredictor = new CurrencyPredictor(new CurrencyInfoRepository());
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(currencyPredictor);

        data.stream()
                .filter(command -> commandParserController.isValidCommand(command))
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNull);
    }

    @Test
    void badPeriodTest() {
        List<String> data = Arrays.asList("rate TRY yesterday", "rate USD month");
        CommandParser commandParser = new CommandParser(ValidatorsFactory.getAll());
        CommandParserController commandParserController = new CommandParserController(commandParser);

        CurrencyPredictor currencyPredictor = new CurrencyPredictor(new CurrencyInfoRepository());
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(currencyPredictor);

        data.stream()
                .filter(command -> commandParserController.isValidCommand(command))
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNull);
    }
}
