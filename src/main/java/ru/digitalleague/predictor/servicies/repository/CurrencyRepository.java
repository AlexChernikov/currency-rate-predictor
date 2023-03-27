package ru.digitalleague.predictor.servicies.repository;

import ru.digitalleague.predictor.enums.Currency;

public class CurrencyRepository {

    private static final String EUR_FILE_NAME = "csv/EUR.csv";
    private static final String TRY_FILE_NAME = "csv/TRY.csv";
    private static final String USD_FILE_NAME = "csv/USD.csv";


    public String getFileNameByCurrency(Currency currency) {
        if (currency != null) {
            switch (currency) {
                case EUR:
                    return EUR_FILE_NAME;
                case TRY:
                    return TRY_FILE_NAME;
                case USD:
                    return USD_FILE_NAME;
                default:
                    return null;
            }
        }

        return null;
    }
}
