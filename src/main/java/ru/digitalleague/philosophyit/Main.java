package ru.digitalleague.philosophyit;

import ru.digitalleague.philosophyit.controllers.CommandParserController;
import ru.digitalleague.philosophyit.controllers.CurrencyPredictorController;
import ru.digitalleague.philosophyit.enums.Currency;
import ru.digitalleague.philosophyit.enums.Period;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    static CommandParserController commandParserController = new CommandParserController();
    static CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        if (commandParserController.validateCommand(command)) {
            Currency currency = commandParserController.parseCurrency(command);
            Period period = commandParserController.parsePeriod(command);

            String predicate;
            predicate = currencyPredictorController.predicate(currency, period);

            LOGGER.log(Level.INFO, predicate);
        }
    }
}