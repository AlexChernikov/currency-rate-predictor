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
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MysticalCurrencyPredictor implements Predictor {

    @Override
    public PredictionResult predicate(List<Currency> currencies, Period period, LocalDateTime localDateTime, Format format) {
        log.info("MysticalCurrencyPredictor predicate started");
        Map<Currency, List<BigDecimal>> currencyAndCounts = new HashMap<>();

        for (Currency currency : currencies) {
            List<CurrencyInfo> currencyInfos = currencyInfoRepository.getCurrencyInfoByCurrency(currency);
            List<BigDecimal> currencyRatePrediction = this.getPredicate(currencyInfos, period, localDateTime);
            currencyAndCounts.put(currency, currencyRatePrediction);
        }

        PredictionResult predicate = format.getFormatter().buildPredication(currencyAndCounts, localDateTime);

        log.info("MysticalCurrencyPredictor predicate finished");
        return predicate;
    }

    private List<BigDecimal> getPredicate(List<CurrencyInfo> currencyInfos, Period period, LocalDateTime localDateTime) {
        List<CurrencyInfo> resultCurrencyInfos = new ArrayList<>();

        for (int i = 0; i < period.getCount(); i++) {
            LocalDateTime lcd = localDateTime;
            lcd = lcd.minusYears(1);
            lcd = lcd.minusYears(new Random().nextInt(10));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            Integer fromIndex = null;
            while (fromIndex == null) {
                for (int j = 0; j < currencyInfos.size(); j++) {
                    if (currencyInfos.get(j).data.equals(formatter.format(lcd))) {
                        fromIndex = j;
                        resultCurrencyInfos.add(currencyInfos.get(j));
                        break;
                    }
                }
                lcd = lcd.minusDays(1);
            }
        }

        List<BigDecimal> counts = count(resultCurrencyInfos);

        return counts;
    }

    public List<BigDecimal> count(List<CurrencyInfo> currencyInfos) {
        List<BigDecimal> currencies = currencyInfos.stream()
                .map(currencyInfo -> currencyInfo.curs.multiply(currencyInfo.nominal))
                .collect(Collectors.toList());

        return currencies;
    }
}
