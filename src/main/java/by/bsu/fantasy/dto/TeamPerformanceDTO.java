package by.bsu.fantasy.dto;

import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.model.TeamIncome;
import by.bsu.fantasy.model.Tournament;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamPerformanceDTO {
    private Long id;
    private Long teamId;
    private Long tournamentId;
    private List<TeamIncomeDTO> incomes;
}
