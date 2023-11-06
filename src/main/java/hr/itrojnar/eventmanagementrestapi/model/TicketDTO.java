package hr.itrojnar.eventmanagementrestapi.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TicketDTO(Long id, BigDecimal price) {
}
