package ru.digitalleague.philosophyit.servicies.formatters;

import ru.digitalleague.philosophyit.interfaces.Formatter;

public class FormattersFactory {

    private FormattersFactory() {
    }

    public static Formatter getCounter() {
        return new DefaultFormatterService();
    }
}
