package com.example.clients.controllers;

import com.example.clients.services.WeatherApiService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WeatherController {

    private final WeatherApiService weatherApiService;

    @GetMapping("/weather")
    public String getClientWeather() {
        //hardcode cluj coordinates
        String lat = "46.773338";
        String lon = "23.621990";
        return weatherApiService.getWeatherForCoordinates(lat, lon);
    }
}
