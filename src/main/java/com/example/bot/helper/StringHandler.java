package com.example.bot.helper;

public class StringHandler {

    public static final String NODE_REGEX = "/node ";

    public static String handleNodeMessage(String inputMessage) {
        return inputMessage.replaceAll(NODE_REGEX, "");
    }
}
