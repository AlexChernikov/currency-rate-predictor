package ru.digitalleague.predictor.controllers;

import lombok.extern.slf4j.Slf4j;
import ru.digitalleague.predictor.entity.ValidationResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.CommandParserService;
import ru.digitalleague.predictor.servicies.CurrencyPredictorService;
import ru.digitalleague.predictor.servicies.validators.ValidatorsFactory;

import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class MainController {
    public void run() {

        CommandParserController commandParserController = new CommandParserController(new CommandParserService(ValidatorsFactory.getAll()));
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(new CurrencyPredictorService());

        printInfo(Currency.values(), "Список доступных для прогноза валют:");
        printInfo(Period.values(), "Список доступных для прогноза периодов:");

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        ValidationResult validationResult = commandParserController.validateCommand(command);

        if (validationResult.isValid()) {
            Currency currency = commandParserController.parseCurrency(command);
            Period period = commandParserController.parsePeriod(command);

            String currencyRatePrediction = currencyPredictorController.predicate(currency, period);

            log.info(currencyRatePrediction);
        } else {
            log.warn(validationResult.getDetailMessage());
        }
    }

    public void printInfo(Object[] objects, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(objects).forEach(v -> stringBuilder.append(" ").append(v));
        log.info(message + stringBuilder);
    }
}
