import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.digitalleague.predictor.controllers.CommandParserController;
import ru.digitalleague.predictor.controllers.CurrencyPredictorController;

import java.util.Arrays;
import java.util.List;

class CurrencyPredictorControllerTest {

    @Test
    void correctDataTest() {
        List<String> data = Arrays.asList("rate TRY tomorrow", "rate USD week");
        CommandParserController commandParserController = new CommandParserController();
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController();

        data.stream()
                .filter(commandParserController::validateCommand)
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void badDataTest() {
        List<String> data = Arrays.asList("rate TRY", "USD week");
        CommandParserController commandParserController = new CommandParserController();
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController();

        data.stream()
                .filter(commandParserController::validateCommand)
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNull);
    }

    @Test
    void badCurrencyTest() {
        List<String> data = Arrays.asList("rate TTT tomorrow", "rate UUU week");
        CommandParserController commandParserController = new CommandParserController();
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController();

        data.stream()
                .filter(commandParserController::validateCommand)
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNull);
    }

    @Test
    void badPeriodTest() {
        List<String> data = Arrays.asList("rate TRY yesterday", "rate USD month");
        CommandParserController commandParserController = new CommandParserController();
        CurrencyPredictorController currencyPredictorController = new CurrencyPredictorController();

        data.stream()
                .filter(commandParserController::validateCommand)
                .map(d -> currencyPredictorController.predicate(commandParserController.parseCurrency(d), commandParserController.parsePeriod(d)))
                .forEach(Assertions::assertNull);
    }
}
