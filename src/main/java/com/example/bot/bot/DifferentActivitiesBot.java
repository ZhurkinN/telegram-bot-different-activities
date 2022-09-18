package com.example.bot.bot;

import com.example.bot.config.BotConfig;
import com.example.bot.entity.Messages;
import com.example.bot.service.MessagesService;
import com.example.bot.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
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
        List<BotCommand> commandList = new ArrayList<>();

        commandList.add(new BotCommand(MENU_COMMAND, MENU_DESCRIPTION));
        commandList.add(new BotCommand(SAVE_NOTE_COMMAND, CREATE_NOTE_DESCRIPTION));
        commandList.add(new BotCommand(SHOW_NOTES_COMMAND, SHOW_NOTES_DESCRIPTION));
        commandList.add(new BotCommand(DELETE_NOTE_COMMAND, DELETE_NOTE_DESCRIPTION));
        try {
            this.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


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

            if (messageText.startsWith(SAVE_NOTE_COMMAND)) {
                answerOnSaveNoteCommand(updateMessage);
            }

            if (messageText.equals(SHOW_NOTES_COMMAND)) {
                answerOnShowNotesCommand(updateMessage);
            }

            if (messageText.startsWith(DELETE_NOTE_COMMAND)) {
                answerOnDeleteNoteCommand(updateMessage);
            }
        }
    }


    private void answerOnDeleteNoteCommand(Message message) {

        try {

            if (messagesService.deleteMessage(message)) {
                sendMessage(message.getChatId(), DELETE_NOTE_ANSWER);
            } else {
                sendMessage(message.getChatId(), DELETE_NOTE_NOT_FOUND_ANSWER);
            }
        } catch (NumberFormatException e) {
            sendMessage(message.getChatId(), DELETE_NOTE_NOT_FOUND_ANSWER);
        }
    }

    @SneakyThrows
    private void answerOnShowNotesCommand(Message message) {

        List<Messages> messagesList = messagesService.findMessagesByChatId(message);

        if (!messagesList.isEmpty()) {

            sendMessage(message.getChatId(), SHOW_NOTES_ANSWER);

            for (Messages msg : messagesList) {
                sendMessage(message.getChatId(), msg.toString());
            }
        } else {
            sendMessage(message.getChatId(), NO_NOTES_ANSWER);
        }
    }

    @SneakyThrows
    private void answerOnSaveNoteCommand(Message message) {

        userService.addUser(message);
        if (messagesService.addMessage(message)) {
            sendMessage(message.getChatId(), NOTE_ANSWER);
        } else {
            sendMessage(message.getChatId(), EMPTY_NOTE_ANSWER);
        }
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
            throw new RuntimeException(e);
        }
    }

}
