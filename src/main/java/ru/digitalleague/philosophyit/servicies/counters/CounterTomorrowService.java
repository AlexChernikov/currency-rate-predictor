package ru.digitalleague.philosophyit.servicies.counters;

import ru.digitalleague.philosophyit.entity.CurrencyInfo;
import ru.digitalleague.philosophyit.interfaces.Counter;

import java.util.Arrays;
import java.util.List;

public class CounterTomorrowService implements Counter {
    @Override
    public List<Double> count(List<CurrencyInfo> currencyInfos) {
        Double sum = currencyInfos.stream()
                .mapToDouble(currencyInfo -> currencyInfo.nominal*currencyInfo.curs)
                .sum();

        return Arrays.asList(sum / currencyInfos.size());
    }
}
