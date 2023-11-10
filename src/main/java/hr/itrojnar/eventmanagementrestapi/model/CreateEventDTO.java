package hr.itrojnar.eventmanagementrestapi.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record CreateEventDTO(String picture, String name, int maxAttendees, String address, String description, LocalDate date, BigDecimal price) {
}
