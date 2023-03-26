package ru.digitalleague.predictor.servicies;

import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.Predictor;
import ru.digitalleague.predictor.servicies.predictors.PredictorsFactory;

public class CurrencyPredictorService {

    public String predicate(Currency currency, Period period) {
        Predictor predictor = PredictorsFactory.getPredictor(currency);
        String currencyRatePrediction = predictor.predicate(period);
        return currencyRatePrediction;
    }
}
