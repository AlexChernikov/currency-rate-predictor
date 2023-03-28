package ru.digitalleague.predictor.controllers;

import lombok.extern.slf4j.Slf4j;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.CommandParser;
import ru.digitalleague.predictor.servicies.CurrencyPredictor;
import ru.digitalleague.predictor.servicies.repository.CurrencyInfoRepository;
import ru.digitalleague.predictor.servicies.validators.ValidatorsFactory;

import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class MainController {
    public void run() {
        CommandParser commandParser = new CommandParser(ValidatorsFactory.getAll());
        CommandParserController commandParserController = new CommandParserController(commandParser);

        CurrencyPredictor currencyPredictor = new CurrencyPredictor(new CurrencyInfoRepository());
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController(currencyPredictor);

        printInfo(Currency.values(), "Список доступных для прогноза валют:");
        printInfo(Period.values(), "Список доступных для прогноза периодов:");


        while (true) {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();

            boolean isValid = commandParserController.isValidCommand(command);

            if (isValid) {
                Currency currency = commandParserController.parseCurrency(command);
                Period period = commandParserController.parsePeriod(command);

                String currencyRatePrediction = currencyPredictorController.predicate(currency, period);

                log.info(currencyRatePrediction);
            } else {
                String detailMessage = commandParserController.getDetailLogMessage(command);
                log.warn(detailMessage);
            }
        }
    }

    public void printInfo(Object[] objects, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(objects).forEach(v -> stringBuilder.append(" ").append(v));
        log.info(message + stringBuilder);
    }
}
