package com.example.coordinator.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {


    @Value("${clients.api.url}")
    private String clientsApiUrl;


    @GetMapping("/weather")
    public String getClientWeather() {
        System.out.println("Making request for client by id in ClientAPI");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = clientsApiUrl + "/weather";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }
}
