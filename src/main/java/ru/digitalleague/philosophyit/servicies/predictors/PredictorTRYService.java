package ru.digitalleague.philosophyit.servicies.predictors;

import ru.digitalleague.philosophyit.entity.CurrencyInfo;
import ru.digitalleague.philosophyit.enums.Period;
import ru.digitalleague.philosophyit.interfaces.Predictor;
import ru.digitalleague.philosophyit.servicies.CSVFileParserService;

import java.util.List;

public class PredictorTRYService implements Predictor {

    private static final String FILE_NAME = "csv/TRY.csv";

    @Override
    public String predicate(Period period) {
        List<CurrencyInfo> currencyInfo = CSVFileParserService.getCurrencyInfo(FILE_NAME);

        String predicate = this.getPredicate(currencyInfo, period);

        return predicate;
    }
}
