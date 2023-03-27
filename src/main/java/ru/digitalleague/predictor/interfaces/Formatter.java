package ru.digitalleague.predictor.interfaces;

import java.math.BigDecimal;
import java.util.List;

public interface Formatter {
    String buildPredication(List<BigDecimal> counts);
}
