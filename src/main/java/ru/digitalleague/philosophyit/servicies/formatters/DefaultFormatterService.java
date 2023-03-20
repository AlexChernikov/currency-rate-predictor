package ru.digitalleague.philosophyit.servicies.formatters;

import ru.digitalleague.philosophyit.interfaces.Formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DefaultFormatterService implements Formatter {

    @Override
    public String buildPredicate(List<Double> counts) {
        StringBuilder stringBuilder = new StringBuilder().append(System.getProperty("line.separator"));

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("EE dd.mm.yyyy", Locale.getDefault());

        for (Double count : counts) {
            stringBuilder.append(dateFormat.format(calendar.getTime()))
                         .append(" - ")
                         .append(String.format("%.2f", count))
                         .append(System.getProperty("line.separator"));

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return stringBuilder.toString();
    }
}
