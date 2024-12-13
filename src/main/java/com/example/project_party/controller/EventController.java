package com.example.project_party.controller;

import com.example.project_party.dto.EventDTO;
import com.example.project_party.model.Event;
import com.example.project_party.model.Users;
import com.example.project_party.service.EventService;
import com.example.project_party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService; // Injection correcte du service UserService

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        if (event.getOrganizer() == null || event.getOrganizer().getId() == null) {
            throw new RuntimeException("Organizer ID is required");
        }

        // Récupérer uniquement l'utilisateur par ID
        Users organizer = userService.findById(event.getOrganizer().getId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        event.setOrganizer(organizer);

        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        // Valider la présence de l'organisateur
        if (event.getOrganizer() == null || event.getOrganizer().getId() == null) {
            throw new RuntimeException("Organizer ID is required");
        }

        Users organizer = userService.findById(event.getOrganizer().getId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        event.setOrganizer(organizer);

        // Mettre à jour l'événement
        Event updatedEvent = eventService.updateEvent(id, event);

        // Retourner l'EventDTO
        return ResponseEntity.ok(new EventDTO(updatedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents()
                .stream()
                .map(EventDTO::new) // Convertir chaque Event en EventDTO
                .toList();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }
}
