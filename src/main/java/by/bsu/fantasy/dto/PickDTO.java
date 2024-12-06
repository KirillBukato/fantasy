package by.bsu.fantasy.dto;

import by.bsu.fantasy.model.Player;
import by.bsu.fantasy.model.Team;
import by.bsu.fantasy.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickDTO {
    private Long id;
    private Double balance;
    private Integer points;

    private List<PlayerDTO> players;

    private List<TeamDTO> teams;

    private Long user_id;
}
