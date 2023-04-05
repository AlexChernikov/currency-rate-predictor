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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LastYearCurrencyPredictor implements Predictor {

    @Override
    public PredictionResult predicate(List<Currency> currencies, Period period, LocalDateTime localDateTime, Format format) {
        log.info("LastYearCurrencyPredictor predicate started");
        Map<Currency, List<BigDecimal>> currencyAndCounts = new HashMap<>();

        for (Currency currency : currencies) {
            List<CurrencyInfo> currencyInfos = currencyInfoRepository.getCurrencyInfoByCurrency(currency);
            List<BigDecimal> currencyRatePrediction = this.getPredicate(currencyInfos, period, localDateTime);
            currencyAndCounts.put(currency, currencyRatePrediction);
        }

        PredictionResult predicate = format.getFormatter().buildPredication(currencyAndCounts, localDateTime);

        log.info("LastYearCurrencyPredictor predicate finished");
        return predicate;
    }

    private List<BigDecimal> getPredicate(List<CurrencyInfo> currencyInfos, Period period, LocalDateTime localDateTime) {
        localDateTime = localDateTime.minusYears(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Integer fromIndex = null;
        while (fromIndex == null) {
            for (int i = 0; i < currencyInfos.size(); i++) {
                if (currencyInfos.get(i).data.equals(formatter.format(localDateTime))) {
                    fromIndex = i;
                    break;
                }
            }
            localDateTime = localDateTime.minusDays(1);
        }

        List<BigDecimal> counts;
        if (period.getCount() > fromIndex) {
            counts = count(currencyInfos.subList(0, fromIndex));
        } else {
            counts = count(currencyInfos.subList(fromIndex - period.getCount(), fromIndex));
        }

        return counts;
    }

    public List<BigDecimal> count(List<CurrencyInfo> currencyInfos) {
        List<BigDecimal> currencies = currencyInfos.stream()
                .map(currencyInfo -> currencyInfo.curs.multiply(currencyInfo.nominal))
                .collect(Collectors.toList());

        return currencies;
    }
}
