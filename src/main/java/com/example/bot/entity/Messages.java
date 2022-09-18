package com.example.bot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@Table(name = "messages")
public class Messages {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;


    @Column(nullable = false, name = "chat_id")
    private Long chatId;

    @Column(name = "time")
    @NotNull
    private Timestamp time;

    @Column(name = "text")
    private String messageText;

    @Column(name = "note_number")
    private Integer noteNumber;

    @Override
    public String toString() {
        return "Номер - " + noteNumber +
                "; \nВремя - " + time +
                "; \nЗаметка - " + messageText;
    }
}
