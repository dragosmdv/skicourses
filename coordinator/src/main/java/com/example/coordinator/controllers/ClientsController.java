package com.example.coordinator.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientsController {

    @Value("${clients.api.url}")
    private String clientsApiUrl;

    @Value("${courses.api.url}")
    private String coursesApiUrl;

    @GetMapping("/{userId}")
    public String getClientByUserId(@PathVariable Long userId) {
        System.out.println("Making request for client by id in ClientAPI");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = clientsApiUrl + "/clients/" + userId;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }

    @PostMapping("/{clientId}/documents")
    public String saveDocument(@PathVariable Long clientId, @RequestBody String document) {
        System.out.println("Making request for saving documents in ClientAPI");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(document, headers);
        String fooResourceUrl
                = clientsApiUrl + "/clients/" + clientId + "/documents";
        ResponseEntity<String> response
                = restTemplate.postForEntity(fooResourceUrl, request, String.class);
        return response.getBody();
    }

    @DeleteMapping("/{clientId}/documents/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long clientId, @PathVariable Long documentId) {
        System.out.println("Making request for saving documents in ClientAPI");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = clientsApiUrl + "/clients/" + clientId + "/documents/" + documentId;
        restTemplate.delete(fooResourceUrl);
        return new ResponseEntity<>("Succesfully deleted document", HttpStatus.ACCEPTED);
    }


    @GetMapping("/courses")
    public String getAllCourses() {
        System.out.println("Making request for courses by id in CoursesAPI");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = coursesApiUrl + "/courses";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }
}
