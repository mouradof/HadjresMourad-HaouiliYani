package com.example.project_party.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String eventType;
    private String location;
    private LocalDateTime dateTime;
    private int maxParticipants;
    private float price;
    private boolean isPaid;
    private String details;
}
