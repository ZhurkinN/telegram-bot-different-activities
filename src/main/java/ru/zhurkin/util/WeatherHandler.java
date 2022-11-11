package ru.zhurkin.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.lang.Math.round;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeatherHandler {
    private static final double KELVIN_ZERO = 273d;

    public static double convertKelvinToCelsius(Double kelvinTemperature) {
        return round((kelvinTemperature - KELVIN_ZERO) * 10) / 10.0;
    }
}
