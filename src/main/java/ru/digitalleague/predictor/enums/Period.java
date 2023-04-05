package ru.digitalleague.predictor.enums;

public enum Period {
    DAY {
        public int getCount() {
            return 1;
        }
    },
    WEEK {
        public int getCount() {
            return 7;
        }
    },
    MONTH {
        public int getCount() {
            return 30;
        }
    },
    YEAR {
        public int getCount() {
            return 365;
        }
    };

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public int getCount() {
        return this.getCount();
    }
}
