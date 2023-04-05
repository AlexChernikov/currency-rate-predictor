package ru.digitalleague.predictor.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.digitalleague.predictor.bot.entity.SendResponse;
import ru.digitalleague.predictor.bot.enums.BotCommandEnum;
import ru.digitalleague.predictor.controllers.CurrencyPredictorService;
import ru.digitalleague.predictor.entity.PredictionResult;
import ru.digitalleague.predictor.enums.Currency;
import ru.digitalleague.predictor.enums.Format;
import ru.digitalleague.predictor.enums.Method;
import ru.digitalleague.predictor.enums.Period;
import ru.digitalleague.predictor.servicies.validators.CurrencyValidatorService;
import ru.digitalleague.predictor.servicies.validators.FormatValidatorService;
import ru.digitalleague.predictor.servicies.validators.MethodValidatorService;
import ru.digitalleague.predictor.servicies.validators.PeriodValidatorService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BotCommandService {

    private final int BUTTONS_AT_ROW = 2;
    private final String SELECT_PERIOD = "Выбрать период";
    private final String SELECT_CURRENCY = "Выбрать валюту";
    private final String SELECT_METHOD = "Выбрать метод";
    private final String FINISH_RATE = "Закончить расчёт";

    private List<Currency> currencies = new ArrayList<>();
    private Period period;
    private LocalDateTime localDateTime;
    private Method method;
    private Format format;

    @Autowired
    private CurrencyValidatorService currencyValidatorService;

    @Autowired
    private PeriodValidatorService periodValidatorService;

    @Autowired
    private MethodValidatorService methodValidatorService;

    @Autowired
    private CurrencyPredictorService currencyPredictorService;

    @Autowired
    private FormatValidatorService formatValidatorService;

    public List<BotCommand> getBotCommands() {
        log.info("Build menu commands");
        List<BotCommand> botCommands = new ArrayList<>();

        for (BotCommandEnum value : BotCommandEnum.values()) {
            String command = "/" + value.name().toLowerCase(Locale.ENGLISH);
            botCommands.add(new BotCommand(command, value.getDescription()));
        }

        return botCommands;
    }

    public SendMessage getSendMessageByCommand(String text) {
        log.info("Parse menu commands");
        SendMessage sendMessage = new SendMessage();
        String command = text.replaceAll("/", "");
        BotCommandEnum botCommandEnum = BotCommandEnum.valueOf(command.toUpperCase(Locale.ROOT));
        switch (botCommandEnum) {
            case START: {
                currencies = new ArrayList<>();
                sendMessage.setText("Привет! Вы можете предсказать курс валют командой /rate");
                break;
            }
            case INFO: {
                sendMessage.setText("Данный бот позволяет предсказывать курс различных валют, используя для этого различные методы.");
                break;
            }
            case HELP: {
                sendMessage.setText("1. Выберите команду /rate или введите её с клавиатуры.\n" +
                        "2. Выберите для расчёта одну или несколько валют. \n" +
                        "3. Выберите период, на который хотите рассчитать курс валют (от дня, до года).\n" +
                        "4. Выберите метод расчёта курса из предложенных.\n" +
                        "5. Выберите способ представления данных - текстовый или графический.\n" +
                        "6. Введите дату начала расчёта курса.");
                break;
            }
            case RATE: {
                currencies = new ArrayList<>();
                sendMessage.setReplyMarkup(setCurrencyValues());
                sendMessage.setText("Выберите валюту и перейдите к выбору периода!");
            }
        }
        log.info("Parse menu commands finished");
        return sendMessage;
    }

    public SendResponse getSendMessageByParams(String messageText) {
        log.info("Parse message text");
        SendMessage sendMessage = new SendMessage();

        if (currencyValidatorService.IsAnInstance(messageText)) {
            currencies.add(Currency.valueOf(messageText));
            sendMessage.setText("Валюта добавлена! Выберите ещё или перейдите к выбору периода!");
            sendMessage.setReplyMarkup(setCurrencyValues());
        } else if (SELECT_PERIOD.equals(messageText)) {
            if (currencies.isEmpty()) {
                sendMessage.setText("Вы не выбрали ни одной валюты!");
            } else {
                sendMessage.setText("Выберите период!");
                sendMessage.setReplyMarkup(setPeriodValues());
            }
        } else if (periodValidatorService.IsAnInstance(messageText)) {
            period = Period.valueOf(messageText);
            sendMessage.setText("Период выбран! Выберете метод!");
            sendMessage.setReplyMarkup(setMethodValues());
        } if (SELECT_CURRENCY.equals(messageText)) {
            sendMessage.setText("Выберите валюту или перейдите к выбору периода!");
            sendMessage.setReplyMarkup(setCurrencyValues());
        } else if (methodValidatorService.IsAnInstance(messageText)) {
            method = Method.valueOf(messageText);
            sendMessage.setText("Метод выбран! Выберите тип представления!");
            sendMessage.setReplyMarkup(setFormatValues());
        } else if (formatValidatorService.IsAnInstance(messageText)) {
            format = Format.valueOf(messageText);
            sendMessage.setText("Формат выбран! Введите дату в формате \"DD.MM.YYYY\"!");
        } else if (SELECT_METHOD.equals(messageText)) {
            sendMessage.setText("Выберите метод или перейдите к выбору формата!");
            sendMessage.setReplyMarkup(setMethodValues());
        } else if (FINISH_RATE.equals(messageText)) {
            sendMessage = getSendMessageByCommand("/rate");
        }
        try {
            DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(messageText, DATEFORMATTER);
            localDateTime = LocalDateTime.of(localDate, LocalDateTime.now().toLocalTime());
            sendMessage.setText("Дата успешно введена!");
            log.info("Parse message finished");
            return prepareMessageAndPhoto(sendMessage);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
        }
        log.info("Parse message finished");

        return new SendResponse(sendMessage, null);
    }

    private SendResponse prepareMessageAndPhoto(SendMessage sendMessage) {
        PredictionResult prediction = currencyPredictorService.predicate(currencies, period, localDateTime, method, format);
        sendMessage.setText(prediction.getTextPrediction());

        if (prediction.getImagePrediction() == null) {
            return new SendResponse(sendMessage, null);
        }

        BufferedImage objBufferedImage = prediction.getImagePrediction();

        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ImageIO.write(objBufferedImage, "png", bas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] byteArray = bas.toByteArray();

        InputStream inputStream = new ByteArrayInputStream(byteArray);
        BufferedImage image = null;
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File outputfile = new File("grath.png");

        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(outputfile));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(FINISH_RATE);
        keyboardRows.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return new SendResponse(sendMessage, sendPhoto);
    }

    private ReplyKeyboardMarkup setCurrencyValues() {
        List<String> filteredCurrencies = Arrays.stream(Currency.values())
                .filter(currency -> !currencies.contains(currency))
                .map(Currency::name)
                .collect(Collectors.toList());

        return getReplyKeyboardMarkup(filteredCurrencies, SELECT_PERIOD);
    }

    private ReplyKeyboardMarkup setPeriodValues() {
        List<String> filteredCurrencies = Arrays.stream(Period.values())
                .filter(period -> !currencies.contains(period))
                .map(Period::name)
                .collect(Collectors.toList());

        return getReplyKeyboardMarkup(filteredCurrencies, SELECT_CURRENCY);
    }

    private ReplyKeyboardMarkup setMethodValues() {
        List<String> filteredCurrencies = Arrays.stream(Method.values())
                .filter(method -> !currencies.contains(method))
                .map(Method::name)
                .collect(Collectors.toList());

        return getReplyKeyboardMarkup(filteredCurrencies, SELECT_PERIOD);
    }

    private ReplyKeyboardMarkup setFormatValues() {
        List<String> filteredCurrencies = Arrays.stream(Format.values())
                .filter(method -> !currencies.contains(method))
                .map(Format::name)
                .collect(Collectors.toList());

        return getReplyKeyboardMarkup(filteredCurrencies, SELECT_METHOD);
    }

    private ReplyKeyboardMarkup getReplyKeyboardMarkup(List<String> buttonsText, String END_PHRASE) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        int i = 0;
        KeyboardRow keyboardRow = new KeyboardRow();
        for (String value : buttonsText) {
            keyboardRow.add(value);
            i++;
            if (i == BUTTONS_AT_ROW) {
                keyboardRows.add(keyboardRow);
                keyboardRow = new KeyboardRow();
                i = 0;
            }
        }
        if (!keyboardRow.isEmpty()) {
            keyboardRows.add(keyboardRow);
        }
        keyboardRow = new KeyboardRow();
        keyboardRow.add(END_PHRASE);
        keyboardRows.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }
}
