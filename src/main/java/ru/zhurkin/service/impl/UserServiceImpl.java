package ru.zhurkin.service.impl;

import ru.zhurkin.dao.UserDAO;
import ru.zhurkin.entity.User;
import ru.zhurkin.service.UserService;
import lombok.RequiredArgsConstructor;
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
