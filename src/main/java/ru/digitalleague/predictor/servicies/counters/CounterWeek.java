package ru.digitalleague.predictor.servicies.counters;

import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.interfaces.Counter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

public class CounterWeek implements Counter {

    @Override
    public List<BigDecimal> count(List<CurrencyInfo> currencyInfos) {
        List<BigDecimal> currencies = currencyInfos.stream()
                .map(currencyInfo -> currencyInfo.curs.multiply(currencyInfo.nominal))
                .collect(Collectors.toList());

        for (int i = 0; i < currencyInfos.size(); i++) {
            currencies = countRecurse(currencies);
        }

        return currencies;
    }

    private List<BigDecimal> countRecurse(List<BigDecimal> currencies) {
        BigDecimal sum = currencies.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal result = sum.divide(BigDecimal.valueOf(currencies.size()), MathContext.DECIMAL32) ;
        currencies.add(0, result);

        return currencies.subList(0, currencies.size() - 1);
    }
}