package com.example.bot.service;

import com.example.bot.entity.Messages;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface MessagesService {

    void addMessage(Message message);

    List<Messages> findMessageByChatId(Message message);
}
