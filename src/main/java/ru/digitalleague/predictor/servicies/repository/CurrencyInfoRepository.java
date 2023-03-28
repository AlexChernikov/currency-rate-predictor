package ru.digitalleague.predictor.servicies.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.enums.Currency;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CurrencyInfoRepository {
    private static final String EUR_FILE_NAME = "csv/EUR.csv";
    private static final String TRY_FILE_NAME = "csv/TRY.csv";
    private static final String USD_FILE_NAME = "csv/USD.csv";
    private final int END_PERIOD = 7;

    public List<CurrencyInfo> getCurrencyInfoByCurrency(Currency currency) {
        String fileName = getFileNameByCurrency(currency);
        InputStream resourceAsStream = CurrencyInfoRepository.class.getClassLoader().getResourceAsStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        List<CurrencyInfo> currencyInfos = new CsvToBeanBuilder<CurrencyInfo>(bufferedReader)
                .withType(CurrencyInfo.class)
                .withSeparator(';')
                .build()
                .parse();

        return currencyInfos;
    }

    private String getFileNameByCurrency(Currency currency) {
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

    public List<CurrencyInfo> getSublistSevenElements(List<CurrencyInfo> currencyInfos) {
        return currencyInfos.subList(0, END_PERIOD);
    }
}
