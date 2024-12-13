package com.example.project_party.dto;

import com.example.project_party.model.Event;

import java.time.LocalDateTime;

public class EventDTO {
    private Long id;
    private String name;
    private LocalDateTime date;
    private String location;
    private String description;
    private Long organizerId;

    public EventDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.date = event.getDate();
        this.location = event.getLocation();
        this.description = event.getDescription();
        this.organizerId = event.getOrganizer() != null ? event.getOrganizer().getId() : null;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }
}
