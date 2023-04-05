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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LinearRegressionPredictor implements Predictor {

    @Override
    public PredictionResult predicate(List<Currency> currencies, Period period, LocalDateTime localDateTime, Format format) {
        log.info("LinearRegressionPredictor predicate started");
        Map<Currency, List<BigDecimal>> currencyAndCounts = new HashMap<>();

        for (Currency currency : currencies) {
            List<CurrencyInfo> currencyInfos = currencyInfoRepository.getCurrencyInfoByCurrency(currency);
            List<BigDecimal> currencyRatePrediction = this.getPredicate(currencyInfos, period);
            currencyAndCounts.put(currency, currencyRatePrediction);
        }

        PredictionResult predicate = format.getFormatter().buildPredication(currencyAndCounts, localDateTime);

        log.info("LinearRegressionPredictor predicate finished");
        return predicate;
    }

    private List<BigDecimal> getPredicate(List<CurrencyInfo> currencyInfos, Period period) {
        List<BigDecimal> counts = count(currencyInfos.subList(0, period.getCount()));

        return counts;
    }

    public List<BigDecimal> count(List<CurrencyInfo> currencyInfos) {
        List<BigDecimal> currencies = new ArrayList<>();
        for (int i = 0; i < currencyInfos.size(); i++) {
            CurrencyInfo currencyInfo = currencyInfos.get(0);
            currencies.add(currencyInfo.curs.multiply(currencyInfo.nominal));
            currencyInfos = countRecurse(currencyInfos);
        }

        return currencies;
    }

    private List<CurrencyInfo> countRecurse(List<CurrencyInfo> currencyInfos) {
        double[] currencies = currencyInfos.stream()
                .map(currencyInfo -> currencyInfo.curs.multiply(currencyInfo.nominal).toBigInteger().doubleValue())
                .mapToDouble(Double::doubleValue)
                .toArray();

        double[] days = currencyInfos.stream()
                .map(currencyInfo -> Double.valueOf(currencyInfo.data.substring(0, 2)))
                .mapToDouble(Double::doubleValue)
                .toArray();

        LinearRegression linearRegression = new LinearRegression(days, currencies);
        BigDecimal result = BigDecimal.valueOf(linearRegression.predict(days[0] + 1));

        CurrencyInfo currencyInfo = new CurrencyInfo();
        currencyInfo.data = String.valueOf(days[0]+1);
        currencyInfo.curs = result;
        currencyInfo.nominal = BigDecimal.valueOf(1);

        currencyInfos.add(0, currencyInfo);

        return currencyInfos.subList(0, currencyInfos.size() - 1);
    }
}
