package com.example.bot.constants;

public class ConstantKeeper {

    public static final String START_COMMAND = "/start";
    public static final String MENU_COMMAND = "/menu";
    public static final String SAVE_NOTE_COMMAND = "/note";
    public static final String SHOW_NOTES_COMMAND = "/show_notes";
    public static final String DELETE_NOTE_COMMAND = "/delete_note";

    public static final String START_ANSWER = """
            Привет, %s. Вот функционал бота:\s
             /menu или /start - главное меню;\s
             /note + {текст заметки} - оставить заметку;\s
             /show_notes - показать все заметки;\s
             /delete_note + {номер заметки}- удалить заметку.""";

    public static final String NOTE_ANSWER = "Ваша заметка успешно записана!";
    public static final String EMPTY_NOTE_ANSWER = "Заметка пуста или отправлена некорректно. Попробуйте ещё раз.";
    public static final String SHOW_NOTES_ANSWER = "Ваши действующие заметки:";
    public static final String NO_NOTES_ANSWER = "У вас нет действующих заметок.";
    public static final String DELETE_NOTE_ANSWER = "Заметка успешно удалена!";
    public static final String DELETE_NOTE_NOT_FOUND_ANSWER = "Заметка не найдена, либо данные введены некорректно.";

    public static final String MENU_DESCRIPTION = "Открыть меню бота";
    public static final String CREATE_NOTE_DESCRIPTION = "Создать новую заметку";
    public static final String SHOW_NOTES_DESCRIPTION = "Показать все заметки";
    public static final String DELETE_NOTE_DESCRIPTION = "Удалить заметку";
}
