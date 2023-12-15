package hr.itrojnar.eventmanagementrestapi.controller;

import hr.itrojnar.eventmanagementrestapi.mapper.EventMapper;
import hr.itrojnar.eventmanagementrestapi.model.EventDTO;
import hr.itrojnar.eventmanagementrestapi.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class EventControllerTest extends BaseControllerTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    @Test
    void getAllEvents() throws Exception {
        mockMvc.perform(
                        get("/events/all")
                                .header("Authorization", getAuthorizationHeader())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getEvent() throws Exception {
        mockMvc.perform(
                        get("/events/{eventId}", 1L)
                                .header("Authorization", getAuthorizationHeader())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createEvent() throws Exception {
        EventDTO eventDTO = EventDTO.builder()
                .name("Test Event")
                .maxAttendees(100)
                .numAttendees(0)
                .address("Test Address")
                .description("Test Description")
                .date(LocalDateTime.now())
                .price(BigDecimal.TEN)
                .build();

        mockMvc.perform(
                        post("/events/create")
                                .header("Authorization", getAuthorizationHeader())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(eventDTO))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Event"));
    }

    @Test
    void updateEvent() throws Exception {
        // Create a new event
        EventDTO eventDTO = EventDTO.builder()
                .name("Update Test Event")
                .maxAttendees(100)
                .numAttendees(50)
                .address("Test Address")
                .description("Test Description")
                .date(LocalDateTime.now())
                .price(BigDecimal.valueOf(20.5))
                .build();

        // Save the event
        EventDTO savedEvent = eventService.save(eventDTO);

        // Modify the event name
        EventDTO updatedEvent = EventDTO.builder()
                .id(savedEvent.id())
                .name("Updated Test Event")
                .maxAttendees(savedEvent.maxAttendees())
                .numAttendees(savedEvent.numAttendees())
                .address(savedEvent.address())
                .description(savedEvent.description())
                .date(savedEvent.date())
                .price(savedEvent.price())
                .build();

        mockMvc.perform(
                        put("/events/update")
                                .header("Authorization", getAuthorizationHeader())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updatedEvent))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Updated Test Event"));
    }

    @Test
    void deleteEvent() throws Exception {
        // Create a new event
        EventDTO eventDTO = EventDTO.builder()
                .name("Delete Test Event")
                .maxAttendees(100)
                .numAttendees(50)
                .address("Test Address")
                .description("Test Description")
                .date(LocalDateTime.now())
                .price(BigDecimal.valueOf(20.5))
                .build();

        // Save the event
        EventDTO savedEvent = eventService.save(eventDTO);

        mockMvc.perform(
                        delete("/events/delete/{eventId}", savedEvent.id()) // Use the correct event ID
                                .header("Authorization", getAuthorizationHeader())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Event with ID " + savedEvent.id() + " deleted successfully."));
    }
}
