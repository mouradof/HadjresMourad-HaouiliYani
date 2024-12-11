package com.example.project_party.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Participant {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private String status; // e.g., "Pending", "Confirmed", "Rejected"
}
