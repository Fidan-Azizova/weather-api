package com.weather.weatherapi.controller;

import com.weather.weatherapi.dto.ApiResponse;
import com.weather.weatherapi.dto.CurrentWeatherDto;
import com.weather.weatherapi.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/current")
    public ApiResponse<CurrentWeatherDto> current(@RequestParam(required = false) String city) {
        return ApiResponse.ok(service.getCurrent(city));
    }
}

