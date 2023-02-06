package com.example.clients.services;

import com.example.clients.models.Client;
import com.example.clients.models.Document;
import com.example.clients.models.dtos.ClientDto;
import com.example.clients.repositories.ClientRepository;
import com.example.clients.repositories.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final DocumentRepository documentRepository;

    public ClientDto getClientByUserId(Long userId) {
        Client client = clientRepository.getByUserId(userId);
        if (client == null) {
            return null;
        }
        List<Document> documents = documentRepository.getAllByClientId(client.getId());

        return new ClientDto(client, documents);
    }
}
