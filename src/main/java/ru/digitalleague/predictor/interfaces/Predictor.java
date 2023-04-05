package ru.digitalleague.predictor.interfaces;

import ru.digitalleague.predictor.entity.PredictionResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Format;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.repository.CurrencyInfoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface Predictor {

    public CurrencyInfoRepository currencyInfoRepository = new CurrencyInfoRepository();

    PredictionResult predicate(List<Currency> currencies, Period period, LocalDateTime localDateTime, Format format);
}
