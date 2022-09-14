package com.example.bot.service;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessagesService {

    void addMessage(Message message);
}
