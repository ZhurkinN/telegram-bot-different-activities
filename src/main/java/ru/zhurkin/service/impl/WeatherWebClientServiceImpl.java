package ru.zhurkin.service.impl;

import ru.zhurkin.dto.weather.WeatherDTO;
import ru.zhurkin.exception.WeatherAPIException;
import ru.zhurkin.service.WeatherWebClientService;
import ru.zhurkin.util.StringHandler;
import ru.zhurkin.constants.APIParametersKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import static ru.zhurkin.constants.ErrorKeeper.WEATHER_API_BAD_REQUEST;

@Service
@PropertySource("application.properties")
public class WeatherWebClientServiceImpl implements WeatherWebClientService {

    @Value("${weather.api.key}")
    private String apiKey;
    private final WebClient client;

    public WeatherWebClientServiceImpl(WebClient.Builder builder) {
        this.client = builder.baseUrl(APIParametersKeeper.WEATHER_API_MAIN_PATH).build();
    }

    @Override
    public WeatherDTO getWeather(String messageText) {
        String cityName = StringHandler.handleWeatherMessage(messageText);
        return client.get()
                .uri(getURI(cityName, apiKey))
                .retrieve()
                .bodyToMono(WeatherDTO.class)
                .blockOptional()
                .orElseThrow(() -> new WeatherAPIException(WEATHER_API_BAD_REQUEST));
    }

    private static String getURI(String cityName, String apiKey) {
        return UriComponentsBuilder.newInstance()
                .path(APIParametersKeeper.WEATHER_URI_PATH)
                .query(APIParametersKeeper.WEATHER_CITY_PARAMETER + cityName)
                .query(APIParametersKeeper.WEATHER_API_KEY_PARAMETER + apiKey)
                .build()
                .toUriString();
    }
}
