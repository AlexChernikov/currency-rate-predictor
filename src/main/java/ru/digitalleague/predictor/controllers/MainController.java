package ru.digitalleague.predictor.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import ru.digitalleague.predictor.entity.PredictionResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Format;
import ru.digitalleague.predictor.enums.Method;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.CommandParser;
import ru.digitalleague.predictor.servicies.validators.ValidatorsFactory;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Controller
public class MainController {

    private CurrencyPredictorService currencyPredictorService = new CurrencyPredictorService();

    public void run() {
        CommandParser commandParser = new CommandParser(ValidatorsFactory.getAll());
        CommandParserController commandParserController = new CommandParserController(commandParser);

        printInfo(Currency.values(), "Список доступных для прогноза валют:");
        printInfo(Period.values(), "Список доступных для прогноза периодов:");


        while (true) {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();

            boolean isValid = commandParserController.isValidCommand(command);

            if (isValid) {
                Currency currency = commandParserController.parseCurrency(command);
                Period period = commandParserController.parsePeriod(command);

                PredictionResult currencyRatePrediction = currencyPredictorService.predicate(List.of(currency), period, LocalDateTime.now(), Method.OLD, Format.TEXT);

                log.info(currencyRatePrediction.getTextPrediction());
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
