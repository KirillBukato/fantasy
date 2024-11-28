package by.bsu.fantasy.dto;

import by.bsu.fantasy.util.IncomeType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamIncomeDTO {
    private Long id;
    private IncomeType type;
    private String description;
    private Integer amount;

    private Long team_id;
}
