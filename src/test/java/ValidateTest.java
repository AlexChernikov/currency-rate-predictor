import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.digitalleague.philosophyit.controllers.CommandParserController;

import java.util.Arrays;
import java.util.List;

class ValidateTest {

    @ParameterizedTest
    @ValueSource(strings = {"rate TRY tomorrow", "rate USD week"})
    void correctDataTest(String value) {
        List<String> data = Arrays.asList(value);
        CommandParserController controller = new CommandParserController();
        Assertions.assertTrue(controller.validateCommand(value));
    }


    @ParameterizedTest
    @ValueSource(strings = {"rate TRY", "USD week"})
    void badDataTest(String value) {
        CommandParserController controller = new CommandParserController();
        Assertions.assertFalse(controller.validateCommand(value));
    }


    @ParameterizedTest
    @ValueSource(strings = {"rate TTT tomorrow", "rate UUU week"})
    void badCurrencyTest(String value) {
        CommandParserController controller = new CommandParserController();
        Assertions.assertFalse(controller.validateCommand(value));
    }


    @ParameterizedTest
    @ValueSource(strings = {"rate TRY yesterday", "rate USD month"})
    void badPeriodTest(String value) {
        CommandParserController controller = new CommandParserController();
        Assertions.assertFalse(controller.validateCommand(value));
    }
}
