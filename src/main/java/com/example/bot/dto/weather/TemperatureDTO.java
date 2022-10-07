package com.example.bot.dto.weather;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TemperatureDTO {
    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Integer pressure;
    private Integer humidity;

    public Double getFeelsLike() {
        return this.feels_like;
    }

    public Double getMaximalTemperature() {
        return this.temp_max;
    }

    public Double getMinimalTemperature() {
        return this.temp_min;
    }
}
