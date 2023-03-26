package ru.digitalleague.predictor.interfaces;

import ru.digitalleague.predictor.enums.Period;

public interface Predictor {

    int END_PERIOD = 7;

    String predicate(Period period);
}
