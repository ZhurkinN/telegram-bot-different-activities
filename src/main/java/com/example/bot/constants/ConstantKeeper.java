package com.example.bot.constants;

public class ConstantKeeper {

    public static final String START_COMMAND = "/start";
    public static final String MENU_COMMAND = "/menu";
    public static final String SAVE_NODE_COMMAND = "/node";
    public static final String SHOW_NODES_COMMAND = "/show_nodes";
    public static final String DELETE_NODE_COMMAND = "/delete_node";

    public static final String START_ANSWER = """
            Привет, %s. Вот функционал бота:\s
             /menu или /start - главное меню;\s
             /node + {текст заметки} - оставить заметку;\s
             /show_nodes - показать все заметки;\s
             /delete_node + {номер заметки}- удалить заметку.""";

    public static final String NODE_ANSWER = "Ваша заметка успешно записана!";
    public static final String EMPTY_NODE_ANSWER = "Заметка пуста или отправлена некорректно. Попробуйте ещё раз.";
    public static final String SHOW_NODES_ANSWER = "Ваши действующие заметки:";
    public static final String NO_NODES_ANSWER = "У вас нет действующих заметок.";
    public static final String DELETE_NODE_ANSWER = "Заметка успешно удалена!";
    public static final String DELETE_NODE_NOT_FOUND_ANSWER = "Заметка не найдена, либо данные введены некорректно.";

}
