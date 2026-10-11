package com.eventease.event;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = {"http://localhost:5173"})
public class EventController {
    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Event> getEvents(@RequestParam(required = false) String search) {
        return (search == null || search.isBlank())
                ? repository.findAll()
                : repository.search(search.trim());
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@Valid @RequestBody Event event) {
        event.setId(null);
        return repository.save(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @Valid @RequestBody Event updated) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        event.setName(updated.getName());
        event.setDescription(updated.getDescription());
        event.setCategory(updated.getCategory());
        event.setEventDateTime(updated.getEventDateTime());
        event.setVenue(updated.getVenue());
        event.setTicketPrice(updated.getTicketPrice());
        event.setCapacity(updated.getCapacity());
        event.setImageUrl(updated.getImageUrl());
        return repository.save(event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        repository.deleteById(id);
    }
}
