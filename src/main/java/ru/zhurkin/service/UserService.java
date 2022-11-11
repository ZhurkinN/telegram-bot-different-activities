package ru.zhurkin.service;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface UserService {

    void addUser(Message message);
}
