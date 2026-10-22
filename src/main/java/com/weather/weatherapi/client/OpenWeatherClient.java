package com.weather.weatherapi.client;

import com.weather.weatherapi.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class OpenWeatherClient {

    private static final Logger log = LoggerFactory.getLogger(OpenWeatherClient.class);

    private final RestClient restClient;
    private final String apiKey;

    public OpenWeatherClient(@Value("${openweather.base-url}") String baseUrl,
                             @Value("${openweather.api-key}") String apiKey) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    public OwmCurrentResponse getCurrent(String city) {
        if (apiKey == null || apiKey.isBlank()) {
            log.error("OPENWEATHER_API_KEY is not configured");
            throw ApiException.internal("Weather service is not configured");
        }
        try {
            return restClient.get()
                    .uri("/weather?q={city}&appid={key}&units=metric", city, apiKey)
                    .retrieve()
                    .body(OwmCurrentResponse.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw ApiException.notFound("City not found: " + city);
        } catch (RestClientException e) {
            log.error("OpenWeatherMap request failed: {}", e.getMessage());
            throw ApiException.internal("Weather service error");
        }
    }
}
