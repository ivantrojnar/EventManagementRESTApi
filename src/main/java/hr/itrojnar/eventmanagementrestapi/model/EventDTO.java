package hr.itrojnar.eventmanagementrestapi.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EventDTO(Long id, String picture, String name, int maxAttendees, int numAttendees, String address, String description, LocalDate date) {
}
