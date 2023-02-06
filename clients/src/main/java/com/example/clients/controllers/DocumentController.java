package com.example.clients.controllers;

import com.example.clients.models.Document;
import com.example.clients.models.dtos.ClientDto;
import com.example.clients.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/clients/{clientId}/documents")
    public Document saveDocument(@PathVariable Long clientId, @RequestBody Document document) {
        document.setClientId(clientId);
        return documentService.saveDocument(document);
    }

    @DeleteMapping("/clients/{clientId}/documents/{documentId}")
    public ResponseEntity<String> saveDocument(@PathVariable Long clientId, @PathVariable Long documentId) {
        documentService.deleteDocument(documentId);
        return new ResponseEntity<>("Succesfully deleted document", HttpStatusCode.valueOf(204));
    }
}
