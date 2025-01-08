package by.bsu.fantasy.dto;

import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.model.PlayerIncome;
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
public class PlayerPerformanceDTO {
    private Long id;
    private Long playerId;
    private Long tournamentId;
    private List<PlayerIncomeDTO> incomes;
}
