package com.example.coordinator.controllers;


import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class VersionController {

    @Value("${clients.api.url}")
    private String clientsApiUrl;

    @Value("${courses.api.url}")
    private String coursesApiUrl;

    @GetMapping("/")
    public String getVersion() {
        System.out.println("making version requests to: " + clientsApiUrl + " and:" + coursesApiUrl);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response
                = restTemplate.getForEntity(clientsApiUrl + "/", String.class);
        String clientVersion = response.getBody();
        response
                = restTemplate.getForEntity(coursesApiUrl + "/", String.class);
        String courseVersion = response.getBody();
        return "Coordinator version = 1.0" + "; " + clientVersion + " ; " + courseVersion;

    }
}
