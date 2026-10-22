package com.weather.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentWeatherDto(
        double temp,
        @JsonProperty("feels_like") double feelsLike,
        @JsonProperty("wind_speed") double windSpeed,
        @JsonProperty("wind_direction") int windDirection,
        int pressure,
        int humidity,
        String sunrise,
        String sunset,
        String icon,
        String description,
        @JsonProperty("city_name") String cityName,
        String datetime) {
}

