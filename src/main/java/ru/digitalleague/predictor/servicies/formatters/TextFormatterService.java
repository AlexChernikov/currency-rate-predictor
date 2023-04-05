package ru.digitalleague.predictor.servicies.formatters;

import lombok.extern.slf4j.Slf4j;
import ru.digitalleague.predictor.entity.PredictionResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.interfaces.Formatter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class TextFormatterService implements Formatter {

    @Override
    public PredictionResult buildPredication(Map<Currency, List<BigDecimal>> currencyAndCounts, LocalDateTime localDateTime) {
        log.info("build predication started");
        StringBuilder stringBuilder = new StringBuilder().append(System.getProperty("line.separator"));

        DateFormat dateFormat = new SimpleDateFormat("EE dd.MM.yyyy", Locale.getDefault());
        for (Currency currency : currencyAndCounts.keySet()) {
            LocalDateTime lcd = localDateTime;
            stringBuilder.append("\n" + currency + ": \n");

            for (BigDecimal count : currencyAndCounts.get(currency)) {
                ZoneId zoneId = ZoneId.systemDefault();
                Date date = Date.from(lcd.atZone(zoneId).toInstant());
                stringBuilder.append(dateFormat.format(date))
                        .append(" - ")
                        .append(String.format("%.2f", count))
                        .append(System.getProperty("line.separator"));

                lcd = lcd.plusDays(1);
            }
        }

        log.info("build predication finished");
        return new PredictionResult(stringBuilder.toString(), null);
    }
}
