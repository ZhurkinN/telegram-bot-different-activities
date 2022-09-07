package com.example.bot.service;

import com.example.bot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.bot.constant.ConstantKeeper.*;

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
    public void onUpdateReceived(Update update) {

        Message updateMessage = update.getMessage();

        if (update.hasMessage() && updateMessage.hasText()) {

            String messageText = updateMessage.getText();

            if (messageText.equals(START_COMMAND)) {

            }

            if (messageText.equals(SAVE_NODE_COMMAND)) {

            }
        }

    }
}
