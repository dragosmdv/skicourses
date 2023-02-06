package com.example.courses.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/")
    public String getVersion() {
        return "CoursesAPI version = 1.0";
    }
}
