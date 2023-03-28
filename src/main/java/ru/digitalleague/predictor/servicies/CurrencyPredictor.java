package ru.digitalleague.predictor.servicies;

import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.Counter;
import ru.digitalleague.predictor.servicies.formatters.FormattersFactory;
import ru.digitalleague.predictor.servicies.repository.CurrencyInfoRepository;

import java.math.BigDecimal;
import java.util.List;

public class CurrencyPredictor {

    private final CurrencyInfoRepository currencyInfoRepository;

    public CurrencyPredictor(CurrencyInfoRepository currencyInfoRepository) {
        this.currencyInfoRepository = currencyInfoRepository;
    }

    public String predicate(Currency currency, Period period) {
        ;

        List<CurrencyInfo> currencyInfos = currencyInfoRepository.getCurrencyInfoByCurrency(currency);

        String currencyRatePrediction = this.getPredicate(currencyInfos, period);
        return currencyRatePrediction;
    }

    private String getPredicate(List<CurrencyInfo> currencyInfos, Period period) {
        Counter counter = period.getCounter();

        List<BigDecimal> counts = counter.count(currencyInfoRepository.getSublistSevenElements(currencyInfos));

        String predicate = FormattersFactory.getFormatter().buildPredication(counts);

        return predicate;
    }
}
