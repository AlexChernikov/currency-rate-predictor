package ru.digitalleague.predictor.controllers;

import org.springframework.stereotype.Service;
import ru.digitalleague.predictor.entity.PredictionResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Format;
import ru.digitalleague.predictor.enums.Method;
import ru.digitalleague.predictor.enums.Period;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyPredictorService {

    public PredictionResult predicate(List<Currency> currencies, Period period, LocalDateTime localDateTime, Method method, Format format) {
        return method.getPredictor().predicate(currencies, period, localDateTime, format);
    }
}
