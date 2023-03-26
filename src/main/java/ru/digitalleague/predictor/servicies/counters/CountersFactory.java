package ru.digitalleague.predictor.servicies.counters;

import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.interfaces.Counter;

public class CountersFactory {

    private CountersFactory() {
    }

    public static Counter getCounter(Period period) {
        switch (period) {
            case TOMORROW: return new CounterTomorrowService();
            case WEEK: return new CounterWeekService();
            default: return null;
        }
    }
}
