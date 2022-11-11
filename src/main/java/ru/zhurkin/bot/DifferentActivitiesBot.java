package ru.zhurkin.bot;

import ru.zhurkin.config.BotConfig;
import ru.zhurkin.entity.Messages;
import ru.zhurkin.exception.WeatherAPIException;
import ru.zhurkin.service.JokeWebClientService;
import ru.zhurkin.service.MessagesService;
import ru.zhurkin.service.UserService;
import ru.zhurkin.service.WeatherWebClientService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static ru.zhurkin.constants.BotAnswersKeeper.*;

@Component
public class DifferentActivitiesBot extends TelegramLongPollingBot {
    @Autowired
    private UserService userService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private WeatherWebClientService weatherService;
    @Autowired
    private JokeWebClientService jokeService;
    @Autowired
    private BotConfig botConfig;

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

            if (messageText.startsWith(WEATHER_COMMAND)) {
                answerOnWeatherCommand(updateMessage);
            }

            if (messageText.startsWith(JOKE_COMMAND)) {
                answerOnJokeCommand(updateMessage);
            }
        }
    }

    private void answerOnJokeCommand(Message message) {
        sendMessage(message.getChatId(), jokeService.getRandomJoke().toString());
    }

    @SneakyThrows
    private void answerOnWeatherCommand(Message message) {
        try {
            sendMessage(message.getChatId(), weatherService.getWeather(message.getText()).toString());
        } catch (WeatherAPIException | WebClientResponseException e) {
            sendMessage(message.getChatId(), WEATHER_NOT_FOUND_ANSWER);
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

            for (Messages messageElement : messagesList) {
                sendMessage(message.getChatId(), messageElement.toString());
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
