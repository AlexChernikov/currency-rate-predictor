package ru.digitalleague.philosophyit.servicies.counters;

import ru.digitalleague.philosophyit.entity.CurrencyInfo;
import ru.digitalleague.philosophyit.interfaces.Counter;

import java.util.List;
import java.util.stream.Collectors;

public class CounterWeekService implements Counter {

    @Override
    public List<Double> count(List<CurrencyInfo> currencyInfos) {
        List<Double> currencies = currencyInfos.stream()
                .map(currencyInfo -> currencyInfo.nominal*currencyInfo.curs)
                .collect(Collectors.toList());

        for (int i = 0; i < currencyInfos.size(); i++) {
            currencies = countRecurse(currencies);
        }

        return currencies;
    }

    private List<Double> countRecurse(List<Double> currencyInfos) {
        Double sum = currencyInfos.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        Double result = sum / currencyInfos.size();
        currencyInfos.add(0, result);

        return currencyInfos.subList(0, currencyInfos.size()-1);
    }
}