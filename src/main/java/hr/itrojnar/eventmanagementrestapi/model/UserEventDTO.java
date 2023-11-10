package hr.itrojnar.eventmanagementrestapi.model;

import hr.itrojnar.eventmanagementrestapi.entities.User;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Builder
public record UserEventDTO(Long id, String name, String address, boolean attending, String picture, int maxAttendees, int numAttendees, String description, LocalDate date, BigDecimal price, List<TicketDTO> tickets) {
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UserEventDTO)) {
            return false;
        }
        UserEventDTO other = (UserEventDTO) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(address, other.address)
                && attending == other.attending
                && Objects.equals(picture, other.picture)
                && maxAttendees == other.maxAttendees
                && numAttendees == other.numAttendees
                && Objects.equals(description, other.description)
                && Objects.equals(date, other.date)
                && Objects.equals(price, other.price)
                && Objects.equals(tickets, other.tickets);
    }
}
