package by.bsu.fantasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDateTime lockDateTime;
    private LocalDateTime unlockDateTime;

    @ManyToMany
    private List<Team> teams;
}
