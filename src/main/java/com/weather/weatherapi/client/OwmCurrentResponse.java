package com.weather.weatherapi.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OwmCurrentResponse(
        List<Weather> weather,
        Main main,
        Wind wind,
        Sys sys,
        long dt,
        int timezone,
        String name) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Weather(String description, String icon) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Main(double temp,
                       @JsonProperty("feels_like") double feelsLike,
                       int pressure,
                       int humidity) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Wind(double speed, int deg) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Sys(long sunrise, long sunset) {}
}
