package com.example.coordinator.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @GetMapping("/{userId}")
    public String getClientByUserId(@PathVariable Long userId) {
        System.out.println("Making request for client by id in ClientAPI");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8082/clients/" + userId;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }

    @PostMapping("/{clientId}/documents")
    public String saveDocument(@PathVariable Long clientId, @RequestBody String document) {
        System.out.println("Making request for saving documents in ClientAPI");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(document,headers);
        String fooResourceUrl
                = "http://localhost:8082/clients/" + clientId + "/documents";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl,request, String.class);
        return response.getBody();
    }

    @DeleteMapping("/{clientId}/documents/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long clientId, @PathVariable Long documentId) {
        System.out.println("Making request for saving documents in ClientAPI");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8082/clients/" + clientId + "/documents/" + documentId;
        restTemplate.delete(fooResourceUrl);
        return new ResponseEntity<>("Succesfully deleted document", HttpStatus.ACCEPTED);
    }


    @GetMapping("/courses")
    public String getAllCourses() {
        System.out.println("Making request for courses by id in CoursesAPI");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8083/courses";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }
}
