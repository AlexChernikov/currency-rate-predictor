package ru.digitalleague.predictor.interfaces;

import ru.digitalleague.predictor.entity.CurrencyInfo;

import java.math.BigDecimal;
import java.util.List;

public interface Counter {
    List<BigDecimal> count(List<CurrencyInfo> currencyInfos);
}