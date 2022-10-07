package com.example.bot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.example.bot.constants.BotAnswersKeeper.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringHandler {
    public static final int WRONG_INPUT_NUMBER = -1;

    public static String handleNoteMessage(String inputMessage) {
        return inputMessage.replaceAll(SAVE_NOTE_COMMAND, "");
    }

    public static Integer handleDeleteNoteMessage(String inputMessage) throws NumberFormatException {

        String handledString = inputMessage.replaceAll(DELETE_NOTE_COMMAND, "")
                .replaceAll("\s", "");
        if (!handledString.isEmpty()) {
            return Integer.parseInt(handledString);
        }
        return WRONG_INPUT_NUMBER;
    }

    public static String handleWeatherMessage(String inputMessage) {
        return inputMessage.replaceAll(WEATHER_COMMAND, "").trim();
    }
}
