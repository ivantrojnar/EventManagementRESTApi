package hr.itrojnar.eventmanagementrestapi.model;

import hr.itrojnar.eventmanagementrestapi.entities.User;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserEventDTO(Long id, String name, String address, boolean attending, String picture, int maxAttendees, int numAttendees, String Description, LocalDate date) {
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if(!(obj instanceof UserEventDTO)) {
            return false;
        }
        return this.id.equals(((UserEventDTO) obj).id);
    }
}
