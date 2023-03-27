package ru.digitalleague.predictor.servicies;

import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.Counter;
import ru.digitalleague.predictor.servicies.counters.CountersFactory;
import ru.digitalleague.predictor.servicies.formatters.FormattersFactory;
import ru.digitalleague.predictor.servicies.repository.CurrencyRepository;

import java.math.BigDecimal;
import java.util.List;

public class CurrencyPredictorService {

    private final int END_PERIOD = 7;

    public String predicate(Currency currency, Period period) {
        CurrencyRepository currencyRepository = new CurrencyRepository();
        String fileName = currencyRepository.getFileNameByCurrency(currency);

        List<CurrencyInfo> currencyInfos = CsvFileParserService.getCurrencyInfo(fileName);

        String currencyRatePrediction = this.getPredicate(currencyInfos, period);
        return currencyRatePrediction;
    }

    public String getPredicate(List<CurrencyInfo> currencyInfos, Period period) {
        Counter counter = CountersFactory.getCounter(period);

        List<BigDecimal> counts = counter.count(currencyInfos.subList(0, END_PERIOD));

        String predicate = FormattersFactory.getFormatter().buildPredication(counts);

        return predicate;
    }
}
