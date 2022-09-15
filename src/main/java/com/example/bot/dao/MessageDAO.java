package com.example.bot.dao;

import com.example.bot.entity.Messages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDAO extends CrudRepository<Messages, Long> {

    @Query("select m from Messages m " +
            "where m.chatId = ?1")
    List<Messages> findByChatId(Long chatId);
}
