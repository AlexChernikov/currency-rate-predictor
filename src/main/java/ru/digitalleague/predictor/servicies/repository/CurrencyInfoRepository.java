package ru.digitalleague.predictor.servicies.repository;

import ru.digitalleague.predictor.entity.CurrencyInfo;

import java.util.List;

public class CurrencyInfoRepository {

    private final int END_PERIOD = 7;

    public List<CurrencyInfo> getSublist(List<CurrencyInfo> currencyInfos) {
        return currencyInfos.subList(0, END_PERIOD);
    }
}
