package ru.digitalleague.predictor.servicies;

import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.Counter;
import ru.digitalleague.predictor.servicies.counters.CountersFactory;
import ru.digitalleague.predictor.servicies.formatters.FormattersFactory;
import ru.digitalleague.predictor.servicies.repository.CurrencyInfoRepository;
import ru.digitalleague.predictor.servicies.repository.CurrencyRepository;

import java.math.BigDecimal;
import java.util.List;

public class CurrencyPredictorService {

    public String predicate(Currency currency, Period period) {
        CurrencyRepository currencyRepository = new CurrencyRepository();

        List<CurrencyInfo> currencyInfos = currencyRepository.getCurrencyInfoByCurrency(currency);

        String currencyRatePrediction = this.getPredicate(currencyInfos, period);
        return currencyRatePrediction;
    }

    private String getPredicate(List<CurrencyInfo> currencyInfos, Period period) {
        CurrencyInfoRepository currencyInfoRepository = new CurrencyInfoRepository();
        Counter counter = CountersFactory.getCounter(period);

        List<BigDecimal> counts = counter.count(currencyInfoRepository.getSublist(currencyInfos));

        String predicate = FormattersFactory.getFormatter().buildPredication(counts);

        return predicate;
    }
}
