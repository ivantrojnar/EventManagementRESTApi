package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.entities.Event;
import hr.itrojnar.eventmanagementrestapi.mapper.EventMapper;
import hr.itrojnar.eventmanagementrestapi.model.EventDTO;
import hr.itrojnar.eventmanagementrestapi.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream()
                .map(eventMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO save(EventDTO newEvent) {
        final Event eventToSave = eventMapper.toEntity(newEvent);
        return eventMapper.mapEntityToDTO(eventRepository.save(eventToSave));
    }

    @Override
    public void deleteById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public EventDTO update(EventDTO updatedEvent) {

        Long eventId = updatedEvent.id();

        Event existingEvent = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + eventId));
        existingEvent.setPicture(updatedEvent.picture());
        existingEvent.setName(updatedEvent.name());
        existingEvent.setMaxAttendees(updatedEvent.maxAttendees());
        existingEvent.setNumAttendees(updatedEvent.numAttendees());
        existingEvent.setAddress(updatedEvent.address());
        existingEvent.setDescription(updatedEvent.description());
        existingEvent.setDate(updatedEvent.date());
        existingEvent.setPrice(updatedEvent.price());

        return eventMapper.mapEntityToDTO(eventRepository.save(existingEvent));
    }
}