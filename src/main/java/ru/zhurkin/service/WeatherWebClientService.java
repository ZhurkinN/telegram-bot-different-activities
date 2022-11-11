package ru.zhurkin.service;

import ru.zhurkin.dto.weather.WeatherDTO;

public interface WeatherWebClientService {
    WeatherDTO getWeather(String cityName);
}
