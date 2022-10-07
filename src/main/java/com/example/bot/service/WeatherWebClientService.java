package com.example.bot.service;

import com.example.bot.dto.weather.WeatherDTO;

public interface WeatherWebClientService {

    WeatherDTO getWeather(String cityName);
}
