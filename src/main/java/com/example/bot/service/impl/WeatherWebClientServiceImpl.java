package com.example.bot.service.impl;

import com.example.bot.dto.weather.WeatherDTO;
import com.example.bot.exception.WeatherAPIException;
import com.example.bot.service.WeatherWebClientService;
import com.example.bot.util.StringHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import static com.example.bot.constants.ErrorKeeper.WEATHER_API_BAD_REQUEST;
import static com.example.bot.constants.WeatherAPIParametersKeeper.*;

@Service
@PropertySource("application.properties")
public class WeatherWebClientServiceImpl implements WeatherWebClientService {

    @Value("${weather.api.key}")
    private static String apiKey;
    private final WebClient client;

    public WeatherWebClientServiceImpl(WebClient.Builder builder) {
        this.client = builder.baseUrl(BASE_URL).build();
    }

    @Override
    public WeatherDTO getWeather(String messageText) {
        String cityName = StringHandler.handleWeatherMessage(messageText);

        return client.get()
                .uri(getURI(cityName))
                .retrieve()
                .bodyToMono(WeatherDTO.class)
                .blockOptional()
                .orElseThrow(() -> new WeatherAPIException(WEATHER_API_BAD_REQUEST));
    }

    private static String getURI(String cityName) {
        return UriComponentsBuilder.newInstance()
                .path(URI_PATH)
                .query(CITY_PARAMETER + cityName)
                .query(API_KEY_PARAMETER + "2feac280daa8bb761bb6a9e5a7a6a91b")
                .build()
                .toUriString();
    }
}
