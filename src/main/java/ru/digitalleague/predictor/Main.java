package ru.digitalleague.predictor;

import lombok.extern.slf4j.Slf4j;
import ru.digitalleague.predictor.controllers.CommandParserController;
import ru.digitalleague.predictor.controllers.CurrencyPredictorController;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.exceptions.CommandFormatException;

import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        CommandParserController commandParserController = new CommandParserController();
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController();

        printInfo(Currency.values(), "Список доступных для прогноза валют:");
        printInfo(Period.values(), "Список доступных для прогноза периодов:");

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if (commandParserController.validateCommand(command)) {

            try {
                Currency currency = commandParserController.parseCurrency(command);
                Period period = commandParserController.parsePeriod(command);

                String currencyRatePrediction = currencyPredictorController.predicate(currency, period);

                log.info(currencyRatePrediction);
            } catch (CommandFormatException e) {
                log.error(e.getDetailMessage());
            }
        }
    }

    public static void printInfo(Object[] objects, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(objects).forEach(v -> stringBuilder.append(" ").append(v));
        log.info(message + stringBuilder);
    }
}