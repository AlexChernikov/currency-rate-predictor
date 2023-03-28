package ru.digitalleague.predictor.servicies.counters;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.interfaces.Counter;
import ru.digitalleague.predictor.servicies.repository.CurrencyInfoRepository;

import java.util.List;

class CounterWeekTest {

    @ParameterizedTest
    @ValueSource(strings = {"USD", "TRY", "EUR"})
    void badPeriodTest(String value) {
        Counter counter = new CounterWeek();
        CurrencyInfoRepository currencyInfoRepository = new CurrencyInfoRepository();
        List<CurrencyInfo> currencyInfoByCurrency = currencyInfoRepository.getCurrencyInfoByCurrency(Currency.valueOf(value));

        counter.count(currencyInfoByCurrency);
    }

}