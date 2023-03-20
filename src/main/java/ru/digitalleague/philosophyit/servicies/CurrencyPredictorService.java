package ru.digitalleague.philosophyit.servicies;

import ru.digitalleague.philosophyit.enums.Currency;
import ru.digitalleague.philosophyit.enums.Period;
import ru.digitalleague.philosophyit.interfaces.Predictor;
import ru.digitalleague.philosophyit.servicies.predictors.PredictorsFactory;

public class CurrencyPredictorService {

    public String predicate(Currency currency, Period period) {
        Predictor predictor = PredictorsFactory.getPredictor(currency);
        String predicate = predictor.predicate(period);
        return predicate;
    }
}
