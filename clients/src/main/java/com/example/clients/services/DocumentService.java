package com.example.clients.services;


import com.example.clients.models.Document;
import com.example.clients.repositories.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public Document saveDocument(Document document) {
        document.setId(0L);
        return documentRepository.save(document);
    }

    public void deleteDocument(Long documentId) {
        Document document = documentRepository.getById(documentId);
        documentRepository.delete(document);
    }
}
