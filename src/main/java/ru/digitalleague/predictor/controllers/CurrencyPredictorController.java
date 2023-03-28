package ru.digitalleague.predictor.controllers;

import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.CurrencyPredictor;

public class CurrencyPredictorController {

    private final CurrencyPredictor currencyPredictorService;

    public CurrencyPredictorController(CurrencyPredictor currencyPredictorService) {
        this.currencyPredictorService = currencyPredictorService;
    }

    public String predicate(Currency currency, Period period) {
        return currencyPredictorService.predicate(currency, period);
    }
}
