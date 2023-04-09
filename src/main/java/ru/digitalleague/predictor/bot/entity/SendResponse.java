package ru.digitalleague.predictor.bot.entity;

import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@Data
public class SendResponse {
    private SendMessage sendMessage;
    private SendPhoto sendPhoto;

    public SendResponse(SendMessage sendMessage, SendPhoto sendPhoto) {
        this.sendMessage = sendMessage;
        this.sendPhoto = sendPhoto;
    }

    public SendResponse(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public SendResponse(SendPhoto sendPhoto) {
        this.sendPhoto = sendPhoto;
    }
}
