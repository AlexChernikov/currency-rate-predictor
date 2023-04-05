package ru.digitalleague.predictor.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.digitalleague.predictor.bot.config.BotConfiguration;
import ru.digitalleague.predictor.bot.entity.SendResponse;
import ru.digitalleague.predictor.bot.service.BotCommandService;

import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private BotConfiguration botConfig;

    @Autowired
    private BotCommandService botCommandService;

    public void initBotCommands() {
        try {
            List<BotCommand> botCommands = botCommandService.getBotCommands();
            execute(new SetMyCommands(botCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error at create and setting bot`s command: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.botName();
    }

    @Override
    public String getBotToken() {
        return botConfig.botToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String messageText = update.getMessage().getText();
        SendMessage sendMessage;
        SendPhoto sendPhoto = null;
        if (update.getMessage().getText().contains("/")) {
            sendMessage = botCommandService.getSendMessageByCommand(messageText);
        } else {
            SendResponse sendResponse = botCommandService.getSendMessageByParams(messageText);
            sendMessage = sendResponse.getSendMessage();
            sendPhoto = sendResponse.getSendPhoto();
        }

        sendMessage.setChatId(update.getMessage().getChatId());

        try {
            log.info("execute message");
            execute(sendMessage);
            log.info("execute message complete");
            if (sendPhoto != null) {
                sendPhoto.setChatId(update.getMessage().getChatId());
                log.info("execute photo");
                execute(sendPhoto);
                log.info("execute photo complete");
            }
        } catch (TelegramApiException e) {
            log.error("Error sending message or photo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
