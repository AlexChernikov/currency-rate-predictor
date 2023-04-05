package ru.digitalleague.predictor.interfaces;

import ru.digitalleague.predictor.entity.PredictionResult;
import ru.digitalleague.predictor.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface Formatter {
    PredictionResult buildPredication(Map<Currency, List<BigDecimal>> currencyAndCounts, LocalDateTime localDateTime);
}
