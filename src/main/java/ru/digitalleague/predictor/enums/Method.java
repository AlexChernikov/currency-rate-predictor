package ru.digitalleague.predictor.enums;

import ru.digitalleague.predictor.interfaces.Predictor;
import ru.digitalleague.predictor.servicies.LastYearCurrencyPredictor;
import ru.digitalleague.predictor.servicies.LinearRegressionPredictor;
import ru.digitalleague.predictor.servicies.MysticalCurrencyPredictor;
import ru.digitalleague.predictor.servicies.OldCurrencyPredictor;

public enum Method {
    OLD {
        public Predictor getPredictor() {
            return new OldCurrencyPredictor();
        }
    },
    LAST_YEAR {
        public Predictor getPredictor() {
            return new LastYearCurrencyPredictor();
        }
    },
    MYSTICAL {
        public Predictor getPredictor() {
            return new MysticalCurrencyPredictor();
        }
    },
    LINEAR_REGRESSION {
        public Predictor getPredictor() {
            return new LinearRegressionPredictor();
        }
    };

    public Predictor getPredictor() {
        return this.getPredictor();
    }
}
