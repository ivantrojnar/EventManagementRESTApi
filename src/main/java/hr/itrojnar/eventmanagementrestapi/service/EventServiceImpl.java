package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.entities.Event;
import hr.itrojnar.eventmanagementrestapi.mapper.EventMapper;
import hr.itrojnar.eventmanagementrestapi.model.EventDTO;
import hr.itrojnar.eventmanagementrestapi.repository.EventRepository;
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
    public void save(EventDTO newEvent) {
        final Event eventToSave = eventMapper.toEntity(newEvent);
        eventRepository.save(eventToSave);
    }
}