package com.example.bot.util;

public class StringHandler {

    public static final String NOTE_REGEX = "/note";
    public static final String DELETE_NOTE_REGEX = "/delete_note";
    public static final int WRONG_INPUT_NUMBER = -1;

    public static String handleNoteMessage(String inputMessage) {
        return inputMessage.replaceAll(NOTE_REGEX, "");
    }

    public static Integer handleDeleteNoteMessage(String inputMessage) throws NumberFormatException {

        String handledString = inputMessage.replaceAll(DELETE_NOTE_REGEX, "")
                .replaceAll("\s", "");
        if (!handledString.isEmpty()) {
            return Integer.parseInt(handledString);
        }
        return WRONG_INPUT_NUMBER;
    }
}
