package com.example.bot.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BotAnswersKeeper {

    /*
    Command's constants
     */
    public static final String START_COMMAND = "/start";
    public static final String MENU_COMMAND = "/menu";
    public static final String SAVE_NOTE_COMMAND = "/note";
    public static final String SHOW_NOTES_COMMAND = "/show_notes";
    public static final String DELETE_NOTE_COMMAND = "/delete_note";
    public static final String WEATHER_COMMAND = "/weather";


    /*
    Answer's constants
     */
    public static final String START_ANSWER = """
            Привет, %s. Вот функционал бота:\s
             /menu или /start - главное меню;\s
             /note + {текст заметки} - оставить заметку;\s
             /show_notes - показать все заметки;\s
             /delete_note + {номер заметки} - удалить заметку;\s
             /weather + {название города по-английски} - узнать текущую погоду.""";
    public static final String NOTE_ANSWER = "Ваша заметка успешно записана!";
    public static final String EMPTY_NOTE_ANSWER = "Заметка пуста или отправлена некорректно. Попробуйте ещё раз.";
    public static final String SHOW_NOTES_ANSWER = "Ваши действующие заметки:";
    public static final String NO_NOTES_ANSWER = "У вас нет действующих заметок.";
    public static final String DELETE_NOTE_ANSWER = "Заметка успешно удалена!";
    public static final String DELETE_NOTE_NOT_FOUND_ANSWER = "Заметка не найдена, либо данные введены некорректно. Попробуйте снова.";
    public static final String WEATHER_NOT_FOUND_ANSWER = "Данные для получения погоды некорректны. Попробуйте снова.";

    /*
    Description's constants
     */
    public static final String MENU_DESCRIPTION = "Открыть меню бота";
    public static final String CREATE_NOTE_DESCRIPTION = "Создать новую заметку";
    public static final String SHOW_NOTES_DESCRIPTION = "Показать все заметки";
    public static final String DELETE_NOTE_DESCRIPTION = "Удалить заметку";
    public static final String WEATHER_DESCRIPTION = "Узнать погоду";
}
