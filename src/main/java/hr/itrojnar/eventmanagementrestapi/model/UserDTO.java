package hr.itrojnar.eventmanagementrestapi.model;

import hr.itrojnar.eventmanagementrestapi.entities.UserType;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record UserDTO(Long id, String username, String password, UserType userType, List<UserEventDTO> allEvents) {
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if(!(obj instanceof UserDTO)) {
            return false;
        }
        return this.id.equals(((UserDTO) obj).id);
    }

    public static UserDTO withEvents(Long id, String username, String password,UserType userType) {
        return new UserDTO(id, username, password, userType, new ArrayList<>());
    }
}
