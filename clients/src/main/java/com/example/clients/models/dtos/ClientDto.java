package com.example.clients.models.dtos;

import com.example.clients.models.Client;
import com.example.clients.models.Document;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
public class ClientDto {

    private Long id;

    private String name;

    private String address;

    private Long userId;

    private List<Document> documents;

    public ClientDto(Client client, List<Document> documents) {
        this.id = client.getId();
        this.name = client.getName();
        this.address = client.getAddress();
        this.userId = client.getUserId();
        this.documents = documents;
    }
}
