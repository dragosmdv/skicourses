package com.example.clients.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiService {

    private final String apiKey = "";

    private final String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s";

    public String getWeatherForCoordinates(String lat, String lon) {
        String finalUrl = String.format(weatherUrl, lat, lon, apiKey);
        System.out.println("Making Weather requests to: " + finalUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(finalUrl, String.class);

        return response.getBody();
    }
}
