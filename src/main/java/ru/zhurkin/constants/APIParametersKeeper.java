package ru.zhurkin.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class APIParametersKeeper {

    public static final String WEATHER_API_MAIN_PATH = "http://api.openweathermap.org/";
    public static final String WEATHER_URI_PATH = "/data/2.5/weather";
    public static final String WEATHER_CITY_PARAMETER = "q=";
    public static final String WEATHER_API_KEY_PARAMETER = "appid=";

    public static final String JOKE_API_MAIN_PATH = "http://anecdotica.ru/";
    public static final String JOKE_URI_PATH = "/api";
    public static final String JOKE_PID_PARAMETER = "pid=";
}
