package ru.digitalleague.philosophyit.servicies.predictors;

import ru.digitalleague.philosophyit.enums.Currency;
import ru.digitalleague.philosophyit.interfaces.Predictor;

public class PredictorsFactory {

    private PredictorsFactory() {
    }

    public static Predictor getPredictor(Currency currency) {
        switch (currency) {
            case EUR: return new PredictorEURService();
            case TRY: return new PredictorTRYService();
            case USD: return new PredictorUSDService();
            default: return null;
        }
    }
}
