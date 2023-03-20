package ru.digitalleague.philosophyit.interfaces;

import ru.digitalleague.philosophyit.entity.CurrencyInfo;
import ru.digitalleague.philosophyit.enums.Period;
import ru.digitalleague.philosophyit.servicies.counters.CountersFactory;
import ru.digitalleague.philosophyit.servicies.formatters.FormattersFactory;

import java.util.List;

public interface Predictor {

    int END_PERIOD = 7;

    String predicate(Period period);

    default String getPredicate(List<CurrencyInfo> currencyInfos, Period period) {
        Counter counter = CountersFactory.getCounter(period);

        List<Double> counts = counter.count(currencyInfos.subList(0, END_PERIOD));

        String predicate = FormattersFactory.getCounter().buildPredicate(counts);

        return predicate;
    }
}
