package ru.digitalleague.predictor.servicies.predictors;

import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.interfaces.Predictor;

public class PredictorsFactory {

    private PredictorsFactory() {
    }

    public static Predictor getPredictor(Currency currency) {
        if (currency != null) {
            switch (currency) {
                case EUR:
                    return new PredictorEurService();
                case TRY:
                    return new PredictorTryService();
                case USD:
                    return new PredictorUsdService();
                default:
                    return null;
            }
        }

        return null;
    }
}
