package com.example.bot.dto.weather;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WeatherDTO {
    private TemperatureDTO main;
    private CloudinessDTO clouds;
    private WindDTO wind;
    private String name;

    @Override
    public String toString() {
        StringBuilder outString = new StringBuilder();
        outString.append("Город: ").append(this.getName()).append("\n")
                .append("Температура: ").append(this.main.getTemp()).append("\n")
                .append("Ощущается как: ").append(this.main.getFeelsLike()).append("\n")
                .append("Максимальная температура: ").append(this.main.getMaximalTemperature()).append("\n")
                .append("Минимальная температура: ").append(this.main.getMinimalTemperature()).append("\n")
                .append("Атмосферное давление: ").append(this.main.getPressure()).append("\n")
                .append("Влажность воздуха: ").append(this.main.getHumidity()).append("\n")
                .append("Скорость ветра: ").append(this.wind.getSpeed()).append("\n")
                .append("Облачность: ").append(this.clouds.getAll());
        return outString.toString();
    }
}
