package ru.digitalleague.predictor.interfaces;

import ru.digitalleague.predictor.enums.Period;

public interface Predictor {

    String predicate(Period period);
}
