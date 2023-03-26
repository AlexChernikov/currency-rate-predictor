package ru.digitalleague.predictor.servicies.counters;

import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.interfaces.Counter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;

public class CounterTomorrowService implements Counter {
    @Override
    public List<BigDecimal> count(List<CurrencyInfo> currencyInfos) {
        BigDecimal sum = currencyInfos.stream()
                .map(currencyInfo -> currencyInfo.curs.multiply(currencyInfo.nominal))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Arrays.asList(sum.divide(BigDecimal.valueOf(currencyInfos.size()), MathContext.DECIMAL32));
    }
}
