package com.example.clients.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/")
    public String getVersion() {
        return "1.0";
    }
}
