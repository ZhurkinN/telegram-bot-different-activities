package ru.zhurkin.dto.weather;

import lombok.*;

import static ru.zhurkin.util.WeatherHandler.convertKelvinToCelsius;

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
                .append("Температура: ").append(convertKelvinToCelsius(this.main.getTemp()))
                .append("°C").append("\n")
                .append("Ощущается как: ").append(convertKelvinToCelsius(this.main.getFeelsLike()))
                .append("°C").append("\n")
                .append("Максимальная температура: ").append(convertKelvinToCelsius(this.main.getMaximalTemperature()))
                .append("°C").append("\n")
                .append("Минимальная температура: ").append(convertKelvinToCelsius(this.main.getMinimalTemperature()))
                .append("°C").append("\n")
                .append("Атмосферное давление: ").append(this.main.getPressure())
                .append(" мм. рт. ст.").append("\n")
                .append("Влажность воздуха: ").append(this.main.getHumidity()).append("\n")
                .append("Скорость ветра: ").append(this.wind.getSpeed()).append(" м/c.").append("\n")
                .append("Облачность: ").append(this.clouds.getAll()).append("%");
        return outString.toString();
    }
}
