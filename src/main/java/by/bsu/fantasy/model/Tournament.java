package by.bsu.fantasy.model;

import by.bsu.fantasy.util.TournamentStatus;
import jakarta.persistence.*;
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
    private TournamentStatus status;

    @OneToMany(mappedBy = "tournament")
    private List<PlayerPerformance> playerPerformances;
    @OneToMany(mappedBy = "tournament")
    private List<TeamPerformance> teamPerformances;
}
