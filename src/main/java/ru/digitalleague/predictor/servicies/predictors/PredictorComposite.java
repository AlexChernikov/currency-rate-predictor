package ru.digitalleague.predictor.servicies.predictors;

import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.Counter;
import ru.digitalleague.predictor.interfaces.Predictor;
import ru.digitalleague.predictor.servicies.counters.CountersFactory;
import ru.digitalleague.predictor.servicies.formatters.FormattersFactory;

import java.math.BigDecimal;
import java.util.List;

public abstract class PredictorComposite implements Predictor {

    public String getPredicate(List<CurrencyInfo> currencyInfos, Period period) {
        Counter counter = CountersFactory.getCounter(period);

        List<BigDecimal> counts = counter.count(currencyInfos.subList(0, END_PERIOD));

        String predicate = FormattersFactory.getCounter().buildPredicate(counts);

        return predicate;
    }
}
