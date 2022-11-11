package ru.zhurkin.service.impl;

import ru.zhurkin.dao.MessageDAO;
import ru.zhurkin.entity.Messages;
import ru.zhurkin.util.StringHandler;
import ru.zhurkin.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    private final MessageDAO dao;

    @Override
    public boolean addMessage(Message message) {

        String node = StringHandler.handleNoteMessage(message.getText());
        Messages objectMessage = new Messages();
        if (!node.isEmpty()) {

            int notesCounter = dao.findByChatId(message.getChatId()).size();

            objectMessage.setChatId(message.getChatId())
                    .setMessageText(node)
                    .setTime(new Timestamp(System.currentTimeMillis()))
                    .setNoteNumber(++notesCounter);

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

        Integer noteNumber = StringHandler.handleDeleteNoteMessage(message.getText());
        if (noteNumber > 0 && !dao.findByChatIdAndNoteNumber(message.getChatId(), noteNumber).isEmpty()) {

            dao.deleteMessagesByChatIdAndNoteNumber(message.getChatId(), noteNumber);

            List<Messages> fixedMessages = dao.findByChatIdAndWhereNoteNumberMore(message.getChatId(), noteNumber);
            for (Messages messageElement : fixedMessages) {

                int previousNoteNumber = messageElement.getNoteNumber();
                messageElement.setNoteNumber(--previousNoteNumber);
            }
            dao.saveAll(fixedMessages);
            return true;
        }
        return false;
    }
}
