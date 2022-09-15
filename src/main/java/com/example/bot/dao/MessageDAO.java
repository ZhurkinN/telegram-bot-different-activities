package com.example.bot.dao;

import com.example.bot.entity.Messages;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MessageDAO extends CrudRepository<Messages, Long> {

    @Query("select m from Messages m " +
            "where m.chatId = ?1")
    List<Messages> findByChatId(Long chatId);

    @Modifying
    @Query("delete from Messages m " +
            "where m.chatId = ?1 and m.nodeNumber = ?2")
    void deleteMessagesByChatIdAndNodeNumber(Long chatId, Integer nodeNumber);

    @Query("select m from Messages m " +
            "where m.chatId = ?1 and m.nodeNumber = ?2")
    List<Messages> findByChatIdAndNodeNumber(Long chatId, Integer nodeNumber);

    @Query("select m from Messages m " +
            "where m.chatId = ?1 and m.nodeNumber > ?2")
    List<Messages> findByChatIdAndWhereNodeNumberMore(Long chatId, Integer nodeNumber);
}
