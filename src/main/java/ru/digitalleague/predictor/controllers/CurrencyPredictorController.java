package ru.digitalleague.predictor.controllers;

import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.CurrencyPredictorService;

public class CurrencyPredictorController {

    private final CurrencyPredictorService currencyPredictorService;

    public CurrencyPredictorController(CurrencyPredictorService currencyPredictorService) {
        this.currencyPredictorService = currencyPredictorService;
    }

    public String predicate(Currency currency, Period period) {
        return currencyPredictorService.predicate(currency, period);
    }
}
