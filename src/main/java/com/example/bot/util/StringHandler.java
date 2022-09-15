package com.example.bot.util;

public class StringHandler {

    public static final String NODE_REGEX = "/node";
    public static final String DELETE_NODE_REGEX = "/delete_node";
    public static final int WRONG_INPUT_NUMBER = -1;

    public static String handleNodeMessage(String inputMessage) {
        return inputMessage.replaceAll(NODE_REGEX, "");
    }

    public static Integer handleDeleteNodeMessage(String inputMessage) {

        String handledString = inputMessage.replaceAll(DELETE_NODE_REGEX, "")
                .replaceAll("\s", "");
        if (!handledString.isEmpty()) {
            return Integer.parseInt(handledString);
        }
        return WRONG_INPUT_NUMBER;
    }
}
