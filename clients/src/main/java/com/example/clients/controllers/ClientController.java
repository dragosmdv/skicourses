package com.example.clients.controllers;

import com.example.clients.models.dtos.ClientDto;
import com.example.clients.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;


    @GetMapping("/clients/{userId}")
    public ClientDto getClientByUserId(@PathVariable Long userId) {
        return clientService.getClientByUserId(userId);
    }
}
