package ru.digitalleague.predictor.servicies.predictors;

import ru.digitalleague.predictor.entity.CurrencyInfo;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.CsvFileParserService;

import java.util.List;

public class PredictorEurService extends PredictorComposite {

    private static final String FILE_NAME = "csv/EUR.csv";

    @Override
    public String predicate(Period period) {
        List<CurrencyInfo> currencyInfos = CsvFileParserService.getCurrencyInfo(FILE_NAME);

        String predicate = this.getPredicate(currencyInfos, period);

        return predicate;
    }
}
