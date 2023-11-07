package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.model.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> findAll();
    void save(EventDTO newEvent);
}
