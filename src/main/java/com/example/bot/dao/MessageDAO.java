package com.example.bot.dao;

import com.example.bot.entity.Messages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDAO extends CrudRepository<Messages, Long> {
}
