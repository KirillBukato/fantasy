package by.bsu.fantasy.dto;

import by.bsu.fantasy.util.TournamentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentDTO {
    private Long id;

    private String name;
    private LocalDateTime lockDateTime;
    private LocalDateTime unlockDateTime;

    private TournamentStatus status;

    private List<PlayerPerformanceDTO> playerPerformances;
    private List<TeamPerformanceDTO> teamPerformances;
}
