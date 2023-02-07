package com.example.coordinator.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
}
