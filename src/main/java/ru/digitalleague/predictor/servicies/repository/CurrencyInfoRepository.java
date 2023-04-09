package ru.digitalleague.predictor.servicies.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.enums.Currency;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Repository
public class CurrencyInfoRepository {
    private static final String EUR_FILE_NAME = "csv/EUR.csv";
    private static final String TRY_FILE_NAME = "csv/TRY.csv";
    private static final String USD_FILE_NAME = "csv/USD.csv";
    private static final String BGN_FILE_NAME = "csv/BGN.csv";
    private static final String AMD_FILE_NAME = "csv/AMD.csv";

    public List<CurrencyInfo> getCurrencyInfoByCurrency(Currency currency) {
        String fileName = null;
        try {
            fileName = getFileNameByCurrency(currency);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
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

    private String getFileNameByCurrency(Currency currency) throws NotFoundException {
        switch (currency) {
            case EUR:
                return EUR_FILE_NAME;
            case TRY:
                return TRY_FILE_NAME;
            case USD:
                return USD_FILE_NAME;
            case BGN:
                return BGN_FILE_NAME;
            case AMD:
                return AMD_FILE_NAME;
            default:
                throw new NotFoundException("File not found!");
        }
    }
}
