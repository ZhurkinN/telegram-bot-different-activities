package com.example.bot.service;

import com.example.bot.config.BotConfig;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.bot.constants.ConstantKeeper.*;

@Component
public class DifferentActivitiesBot extends TelegramLongPollingBot {

    final BotConfig botConfig;

    public DifferentActivitiesBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return this.botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return this.botConfig.getBotToken();
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        Message updateMessage = update.getMessage();

        if (update.hasMessage() && updateMessage.hasText()) {
            String messageText = updateMessage.getText();

            if (messageText.equals(START_COMMAND)) {
                onStartCommandReceived(updateMessage.getChatId(), updateMessage.getChat().getFirstName());
            }

//            if (messageText.equals(SAVE_NODE_COMMAND)) {
//
//            }
        }

    }

    @SneakyThrows
    private void onStartCommandReceived(long chatId, String senderName) {
        String answer = String.format(START_ANSWER, senderName);

        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String messageText) {
        SendMessage message = new SendMessage(String.valueOf(chatId), messageText);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

}
