package com.example.bot.constants;

public class ConstantKeeper {

    public static final String START_COMMAND = "/start";
    public static final String MENU_COMMAND = "/menu";
    public static final String SAVE_NODE_COMMAND = "/node";
    public static final String SHOW_NODES_COMMAND = "/show_nodes";

    public static final String START_ANSWER = "Привет, %s. Вот функционал бота: \n " +
            "/menu или /start - главное меню; \n " +
            "/node + {текст заметки} - оставить заметку; \n " +
            "/show_nodes - показать все заметки.";

    public static final String NODE_ANSWER = "Ваша заметка успешно записана!";

    public static final String SHOW_NODES_ANSWER = "Ваши действующие заметки:";
}
