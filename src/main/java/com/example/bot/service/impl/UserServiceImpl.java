package com.example.bot.service.impl;

import com.example.bot.dao.UserDAO;
import com.example.bot.entity.User;
import com.example.bot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO dao;

    @Override
    public void addUser(Message message) {

        if (dao.findById(message.getChatId()).isEmpty()) {

            User user = new User();
            Chat chat = message.getChat();

            user.setId(message.getChatId())
                    .setUserName(chat.getUserName())
                    .setFirstName(chat.getFirstName())
                    .setSecondName(chat.getLastName());

            dao.save(user);
        }
    }
}
