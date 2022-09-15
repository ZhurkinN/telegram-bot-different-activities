package com.example.bot.service.impl;

import com.example.bot.dao.MessageDAO;
import com.example.bot.entity.Messages;
import com.example.bot.helper.StringHandler;
import com.example.bot.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    MessageDAO dao;

    @Override
    public void addMessage(Message message) {

        String node = StringHandler.handleNodeMessage(message.getText());
        Messages objectMessage = new Messages();

        objectMessage.setChatId(message.getChatId())
                .setMessageText(node)
                .setTime(new Timestamp(System.currentTimeMillis()));

        dao.save(objectMessage);
    }

    @Override
    public List<Messages> findMessageByChatId(Message message) {

        return dao.findByChatId(message.getChatId());
    }


}
