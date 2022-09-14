package com.example.bot.service.impl;

import com.example.bot.dao.MessageDAO;
import com.example.bot.entity.Messages;
import com.example.bot.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessagesService {

    @Autowired
    MessageDAO dao;

    @Override
    public void addMessage(Message message) {

        Messages objectMessage = new Messages();
        objectMessage.setChatId(message.getChatId())
                .setMessageText(message.getText())
                .setTime(new Timestamp(System.currentTimeMillis()));

        dao.save(objectMessage);
    }
}
