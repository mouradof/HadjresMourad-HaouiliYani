package com.example.project_party.controller;

import com.example.project_party.dto.EventDTO;
import com.example.project_party.model.Event;
import com.example.project_party.model.Users;
import com.example.project_party.service.EventService;
import com.example.project_party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<EventDTO> events = eventService.getAllEvents((Pageable) PageRequest.of(page, size))
                .map(EventDTO::new);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        if (event.getOrganizer() == null || event.getOrganizer().getId() == null) {
            throw new RuntimeException("Organizer ID is required");
        }

        Users organizer = userService.findById(event.getOrganizer().getId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        event.setOrganizer(organizer);

        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        if (event.getOrganizer() == null || event.getOrganizer().getId() == null) {
            throw new RuntimeException("Organizer ID is required");
        }

        Users organizer = userService.findById(event.getOrganizer().getId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        event.setOrganizer(organizer);

        Event updatedEvent = eventService.updateEvent(id, event);

        return ResponseEntity.ok(new EventDTO(updatedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
