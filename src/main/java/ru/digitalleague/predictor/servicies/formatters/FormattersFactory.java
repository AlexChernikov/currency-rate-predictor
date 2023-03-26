package ru.digitalleague.predictor.servicies.formatters;

import ru.digitalleague.predictor.interfaces.Formatter;

public class FormattersFactory {

    private FormattersFactory() {
    }

    public static Formatter getCounter() {
        return new DefaultFormatterService();
    }
}
