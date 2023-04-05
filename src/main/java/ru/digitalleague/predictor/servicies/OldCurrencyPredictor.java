package ru.digitalleague.predictor.servicies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.entity.PredictionResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Format;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.Predictor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OldCurrencyPredictor implements Predictor {

    @Override
    public PredictionResult predicate(List<Currency> currencies, Period period, LocalDateTime localDateTime, Format format) {
        log.info("OldCurrencyPredictor predicate started");
        Map<Currency, List<BigDecimal>> currencyAndCounts = new HashMap<>();

        for (Currency currency : currencies) {
            List<CurrencyInfo> currencyInfos = currencyInfoRepository.getCurrencyInfoByCurrency(currency);
            List<BigDecimal> currencyRatePrediction = this.getPredicate(currencyInfos, period);
            currencyAndCounts.put(currency, currencyRatePrediction);
        }

        PredictionResult predicate = format.getFormatter().buildPredication(currencyAndCounts, localDateTime);

        log.info("OldCurrencyPredictor predicate started");
        return predicate;
    }

    private List<BigDecimal> getPredicate(List<CurrencyInfo> currencyInfos, Period period) {
        List<BigDecimal> counts = count(currencyInfos.subList(0, period.getCount()));

        return counts;
    }

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
