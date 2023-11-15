package hr.itrojnar.eventmanagementrestapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "picture", length = 1000000)
    private String picture;

    @Column(name = "name")
    private String name;

    @Column(name = "maxAttendees")
    private int maxAttendees;

    @Column(name = "numAttendees")
    private int numAttendees;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "price")
    private BigDecimal price;
}
