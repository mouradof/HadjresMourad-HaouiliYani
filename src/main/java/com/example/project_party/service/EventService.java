package com.example.project_party.service;

import com.example.project_party.model.Event;
import com.example.project_party.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @CachePut(value = "eventsCache", key = "#event.id")
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @CachePut(value = "eventsCache", key = "#id")
    public Event updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setOrganizer(updatedEvent.getOrganizer());

        return eventRepository.save(existingEvent);
    }

    @CacheEvict(value = "eventsCache", key = "#id")
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @CacheEvict(value = "eventsCache", key = "#id")
    public Page<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    @Cacheable(value = "eventsCache", key = "#id")
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }
}
