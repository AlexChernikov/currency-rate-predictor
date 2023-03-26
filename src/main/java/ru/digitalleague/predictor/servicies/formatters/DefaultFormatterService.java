package ru.digitalleague.predictor.servicies.formatters;

import ru.digitalleague.predictor.interfaces.Formatter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DefaultFormatterService implements Formatter {

    @Override
    public String buildPredicate(List<BigDecimal> counts) {
        StringBuilder stringBuilder = new StringBuilder().append(System.getProperty("line.separator"));

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("EE dd.mm.yyyy", Locale.getDefault());

        for (BigDecimal count : counts) {
            stringBuilder.append(dateFormat.format(calendar.getTime()))
                         .append(" - ")
                         .append(String.format("%.2f", count))
                         .append(System.getProperty("line.separator"));

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return stringBuilder.toString();
    }
}
