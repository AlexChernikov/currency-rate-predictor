package ru.digitalleague.predictor.enums;

import ru.digitalleague.predictor.interfaces.Counter;
import ru.digitalleague.predictor.servicies.counters.CounterTomorrow;
import ru.digitalleague.predictor.servicies.counters.CounterWeek;

public enum Period {
    TOMORROW {
        public Counter getCounter() {
            return new CounterTomorrow();
        }
    },
    WEEK {
        public Counter getCounter() {
            return new CounterWeek();
        }
    };

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public Counter getCounter() {
        return this.getCounter();
    }
}
