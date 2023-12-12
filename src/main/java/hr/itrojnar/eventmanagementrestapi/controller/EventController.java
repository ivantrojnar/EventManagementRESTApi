package hr.itrojnar.eventmanagementrestapi.controller;

import hr.itrojnar.eventmanagementrestapi.model.EventDTO;
import hr.itrojnar.eventmanagementrestapi.service.EventService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("events")
public class EventController {

    private final EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long eventId) { return ResponseEntity.ok(eventService.findEvent(eventId)); }

    @PostMapping("/create")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        EventDTO savedEvent = eventService.save(eventDTO);
        return ResponseEntity.ok(savedEvent);
    }

    @PutMapping("/update")
    public ResponseEntity<EventDTO> updateEvent(@RequestBody EventDTO updatedEventDTO) {
        EventDTO updatedEvent = eventService.update(updatedEventDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteById(eventId);
        return ResponseEntity.ok("Event with ID " + eventId + " deleted successfully.");
    }
}
