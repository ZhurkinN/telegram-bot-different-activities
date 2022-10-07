package com.example.bot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WeatherAPIException extends RuntimeException {
    public WeatherAPIException(String message) {
        super(message);
    }
}
