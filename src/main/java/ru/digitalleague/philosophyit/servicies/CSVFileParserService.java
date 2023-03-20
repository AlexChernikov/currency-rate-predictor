package ru.digitalleague.philosophyit.servicies;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.digitalleague.philosophyit.entity.CurrencyInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CSVFileParserService {

    private CSVFileParserService() {

    }

    public static List<CurrencyInfo> getCurrencyInfo(String fileName) {
        InputStream resourceAsStream = CSVFileParserService.class.getClassLoader().getResourceAsStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        List<CurrencyInfo> currencyInfos = new CsvToBeanBuilder<CurrencyInfo>(bufferedReader)
                .withType(CurrencyInfo.class)
                .withSeparator(';')
                .build()
                .parse();

        return currencyInfos;
    }
}
