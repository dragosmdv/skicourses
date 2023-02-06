package com.example.coordinator.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/")
    public String getVersion() {
        return "ClientsAPI version = 1.0";
    }
}
