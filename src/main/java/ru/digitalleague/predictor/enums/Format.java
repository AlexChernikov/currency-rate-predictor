package ru.digitalleague.predictor.enums;

import ru.digitalleague.predictor.interfaces.Formatter;
import ru.digitalleague.predictor.servicies.formatters.JFreeFormatterService;
import ru.digitalleague.predictor.servicies.formatters.TextFormatterService;

public enum Format {
    TEXT {
        public Formatter getFormatter() {
            return new TextFormatterService();
        }
    },
    GRAPH {
        public Formatter getFormatter() {
            return new JFreeFormatterService();
        }
    };

    public Formatter getFormatter() {
        return this.getFormatter();
    }
}
