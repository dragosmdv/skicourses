package com.example.coordinator.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document {


    private Long id;

    private String name;

    @Column(columnDefinition="TEXT")
    private String encryption;

    private Long clientId;
}
