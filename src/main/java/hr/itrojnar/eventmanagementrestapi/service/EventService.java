package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.model.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> findAll();
    EventDTO save(EventDTO newEvent);
    void deleteById(Long eventId);
    EventDTO update(EventDTO updatedEvent);

    EventDTO findEvent(Long eventId);
}
