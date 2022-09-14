package com.example.bot.bot;

import com.example.bot.config.BotConfig;
import com.example.bot.service.MessagesService;
import com.example.bot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.bot.constants.ConstantKeeper.*;

@Component
public class DifferentActivitiesBot extends TelegramLongPollingBot {

    @Autowired
    private UserService userService;
    @Autowired
    private MessagesService messagesService;
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
                answerOnStartCommand(updateMessage.getChatId(), updateMessage.getChat().getFirstName());
            }

            if (messageText.equals(SAVE_NODE_COMMAND)) {
                answerOnNodeCommand(updateMessage);
            }
        }

    }

    @SneakyThrows
    private void answerOnNodeCommand(Message message) {

        userService.addUser(message);
        messagesService.addMessage(message);
        sendMessage(message.getChatId(), NODE_ANSWER);
    }

    @SneakyThrows
    private void answerOnStartCommand(long chatId, String senderName) {
        String answer = String.format(START_ANSWER, senderName);

        sendMessage(chatId, answer);
    }

    public void sendMessage(long chatId, String messageText) {
        SendMessage message = new SendMessage(String.valueOf(chatId), messageText);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

}
