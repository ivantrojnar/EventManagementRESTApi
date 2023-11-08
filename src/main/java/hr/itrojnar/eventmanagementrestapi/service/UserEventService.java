package hr.itrojnar.eventmanagementrestapi.service;

import hr.itrojnar.eventmanagementrestapi.entities.User;
import hr.itrojnar.eventmanagementrestapi.entities.UserEvent;

import java.util.List;

public interface UserEventService {
    List<UserEvent> findByUser(User user);
}
