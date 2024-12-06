package by.bsu.fantasy.dto;

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

    private List<Long> team_ids;
}
