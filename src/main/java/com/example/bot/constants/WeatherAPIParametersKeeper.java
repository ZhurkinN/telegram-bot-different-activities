package com.example.bot.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeatherAPIParametersKeeper {

    public static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String URI_PATH = "/data/2.5/weather";
    public static final String CITY_PARAMETER = "q=";
    public static final String API_KEY_PARAMETER = "appid=";
}
