package ru.zhurkin.service;

import ru.zhurkin.dto.joke.JokeDTO;

public interface JokeWebClientService {
    JokeDTO getRandomJoke();
}
