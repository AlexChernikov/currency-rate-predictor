package ru.digitalleague.predictor.enums;

public enum Period {
    TOMORROW,
    WEEK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
