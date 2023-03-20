package ru.digitalleague.philosophyit.interfaces;

import ru.digitalleague.philosophyit.entity.CurrencyInfo;

import java.util.List;

public interface Counter {
    List<Double> count(List<CurrencyInfo> currencyInfos);
}