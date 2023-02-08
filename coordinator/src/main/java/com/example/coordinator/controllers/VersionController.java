package com.example.coordinator.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class VersionController {

    @GetMapping("/")
    public String getVersion() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8082/";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        String clientVersion = response.getBody();

        fooResourceUrl
                = "http://localhost:8083/";
        response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        String courseVersion = response.getBody();
        return "Coordinator version = 1.0" + "; " + clientVersion + " ; " + courseVersion;

    }
}
