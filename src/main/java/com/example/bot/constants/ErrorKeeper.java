package com.example.bot.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorKeeper {
    public static final String WEATHER_API_BAD_REQUEST = "Сервер определения погоды не отвечает!";
}
