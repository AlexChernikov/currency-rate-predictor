package ru.digitalleague.philosophyit.controllers;

import ru.digitalleague.philosophyit.enums.Currency;
import ru.digitalleague.philosophyit.enums.Period;
import ru.digitalleague.philosophyit.servicies.CurrencyPredictorService;

public class CurrencyPredictorController {

    CurrencyPredictorService currencyPredictorService = new CurrencyPredictorService();

    public String predicate(Currency currency, Period period) {
        return currencyPredictorService.predicate(currency, period);
    }
}
