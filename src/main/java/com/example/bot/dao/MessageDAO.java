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
            "where m.chatId = ?1 and m.noteNumber = ?2")
    void deleteMessagesByChatIdAndNoteNumber(Long chatId, Integer noteNumber);

    @Query("select m from Messages m " +
            "where m.chatId = ?1 and m.noteNumber = ?2")
    List<Messages> findByChatIdAndNoteNumber(Long chatId, Integer noteNumber);

    @Query("select m from Messages m " +
            "where m.chatId = ?1 and m.noteNumber > ?2")
    List<Messages> findByChatIdAndWhereNoteNumberMore(Long chatId, Integer noteNumber);
}
