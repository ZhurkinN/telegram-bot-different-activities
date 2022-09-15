package com.example.bot.service.impl;

import com.example.bot.dao.MessageDAO;
import com.example.bot.entity.Messages;
import com.example.bot.util.StringHandler;
import com.example.bot.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    MessageDAO dao;

    @Override
    public boolean addMessage(Message message) {

        String node = StringHandler.handleNodeMessage(message.getText());
        Messages objectMessage = new Messages();
        if (!node.isEmpty()) {

            int nodesCounter = dao.findByChatId(message.getChatId()).size();

            objectMessage.setChatId(message.getChatId())
                    .setMessageText(node)
                    .setTime(new Timestamp(System.currentTimeMillis()))
                    .setNodeNumber(++nodesCounter);

            dao.save(objectMessage);
            return true;
        }
        return false;
    }

    @Override
    public List<Messages> findMessagesByChatId(Message message) {

        return dao.findByChatId(message.getChatId());
    }

    @Override
    public boolean deleteMessage(Message message) {

        Integer nodeNumber = StringHandler.handleDeleteNodeMessage(message.getText());
        if (nodeNumber > 0 && !dao.findByChatIdAndNodeNumber(message.getChatId(), nodeNumber).isEmpty()) {

            dao.deleteMessagesByChatIdAndNodeNumber(message.getChatId(), nodeNumber);

            List<Messages> fixedMessages = dao.findByChatIdAndWhereNodeNumberMore(message.getChatId(), nodeNumber);
            for (Messages msg : fixedMessages) {

                int previousNodeNumber = msg.getNodeNumber();
                msg.setNodeNumber(--previousNodeNumber);
            }
            dao.saveAll(fixedMessages);
            return true;
        }
        return false;
    }
}
