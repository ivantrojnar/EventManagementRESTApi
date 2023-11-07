package hr.itrojnar.eventmanagementrestapi.mapper;

import hr.itrojnar.eventmanagementrestapi.entities.Event;
import hr.itrojnar.eventmanagementrestapi.model.EventDTO;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventDTO mapEntityToDTO(Event entity) {
        return EventDTO.builder()
                .id(entity.getId())
                .picture(entity.getPicture())
                .name(entity.getName())
                .maxAttendees(entity.getMaxAttendees())
                .numAttendees(entity.getNumAttendees())
                .address(entity.getAddress())
                .description(entity.getDescription())
                .date(entity.getDate())
                .build();
    }

    public Event toEntity(EventDTO dto) {
        Event event = new Event();
        event.setId(dto.id());
        event.setPicture(dto.picture());
        event.setName(dto.name());
        event.setMaxAttendees(dto.maxAttendees());
        event.setNumAttendees(dto.numAttendees());
        event.setAddress(dto.address());
        event.setDescription(dto.description());
        event.setDate(dto.date());
        return event;
    }
}