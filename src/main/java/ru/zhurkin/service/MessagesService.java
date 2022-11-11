package ru.zhurkin.service;

import ru.zhurkin.entity.Messages;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface MessagesService {

    boolean addMessage(Message message);
    List<Messages> findMessagesByChatId(Message message);
    boolean deleteMessage(Message message);
}
