package com.example.bot.bot;

import com.example.bot.config.BotConfig;
import com.example.bot.entity.Messages;
import com.example.bot.service.MessagesService;
import com.example.bot.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

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

            if (messageText.equals(START_COMMAND) || messageText.equals(MENU_COMMAND)) {
                answerOnStartCommand(updateMessage.getChatId(), updateMessage.getChat().getFirstName());
            }

            if (messageText.startsWith(SAVE_NODE_COMMAND)) {
                answerOnSaveNodeCommand(updateMessage);
            }

            if (messageText.equals(SHOW_NODES_COMMAND)) {
                answerOnShowNodesCommand(updateMessage);
            }
        }

    }

    private void answerOnShowNodesCommand(Message message) {

        List<Messages> messagesList = messagesService.findMessageByChatId(message);

        sendMessage(message.getChatId(), SHOW_NODES_ANSWER);

        for (Messages msg : messagesList) {
            sendMessage(message.getChatId(), msg.toString());
        }


    }

    @SneakyThrows
    private void answerOnSaveNodeCommand(Message message) {

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
