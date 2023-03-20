package ru.digitalleague.philosophyit.servicies.counters;

import ru.digitalleague.philosophyit.enums.Period;
import ru.digitalleague.philosophyit.interfaces.Counter;

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
