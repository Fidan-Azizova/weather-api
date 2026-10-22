package com.weather.weatherapi.service;

import com.weather.weatherapi.client.OpenWeatherClient;
import com.weather.weatherapi.client.OwmCurrentResponse;
import com.weather.weatherapi.dto.CurrentWeatherDto;
import com.weather.weatherapi.exception.ApiException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    private final OpenWeatherClient client;

    public WeatherService(OpenWeatherClient client) {
        this.client = client;
    }

    public CurrentWeatherDto getCurrent(String city) {
        if (city == null || city.isBlank()) {
            throw ApiException.badRequest("city parameter is required");
        }

        OwmCurrentResponse r = client.getCurrent(city.trim());
        if (r == null || r.main() == null || r.wind() == null || r.sys() == null
                || r.weather() == null || r.weather().isEmpty()) {
            throw ApiException.internal("Invalid response from weather service");
        }

        ZoneOffset offset = ZoneOffset.ofTotalSeconds(r.timezone());
        OwmCurrentResponse.Weather w = r.weather().get(0);

        return new CurrentWeatherDto(
                r.main().temp(),
                r.main().feelsLike(),
                r.wind().speed(),
                r.wind().deg(),
                r.main().pressure(),
                r.main().humidity(),
                toIso(r.sys().sunrise(), offset),
                toIso(r.sys().sunset(), offset),
                w.icon(),
                w.description(),
                r.name(),
                toIso(r.dt(), offset));
    }

    private String toIso(long epochSeconds, ZoneOffset offset) {
        return OffsetDateTime.ofInstant(Instant.ofEpochSecond(epochSeconds), offset)
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}

