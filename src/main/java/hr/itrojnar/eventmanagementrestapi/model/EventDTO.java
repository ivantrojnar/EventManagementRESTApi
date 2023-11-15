package hr.itrojnar.eventmanagementrestapi.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record EventDTO(Long id, String picture, String name, int maxAttendees, int numAttendees, String address, String description, LocalDateTime date, BigDecimal price) {
}
