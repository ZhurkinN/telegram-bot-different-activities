package ru.zhurkin.service.impl;

import ru.zhurkin.dto.joke.JokeDTO;
import ru.zhurkin.service.JokeWebClientService;
import ru.zhurkin.constants.APIParametersKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource("application.properties")
public class JokeWebClientServiceImpl implements JokeWebClientService {

    @Value("${joke.api.pid}")
    private String apiPID;
    private final WebClient client;

    public JokeWebClientServiceImpl(WebClient.Builder builder) {
        this.client = builder.baseUrl(APIParametersKeeper.JOKE_API_MAIN_PATH).build();
    }

    @Override
    public JokeDTO getRandomJoke() {

        return client.get()
                .uri(getURI(apiPID))
                .retrieve()
                .bodyToMono(JokeDTO.class)
                .block();
    }

    private static String getURI(String apiPID) {
        return UriComponentsBuilder.newInstance()
                .path(APIParametersKeeper.JOKE_URI_PATH)
                .query(APIParametersKeeper.JOKE_PID_PARAMETER + apiPID)
                .query("method=getRandItem&format=json&charset=cp1251&uts=1490050901&markup=1&hash=0123456789abcdef0123456789abcdef")
                .build()
                .toUriString();

    }
}
