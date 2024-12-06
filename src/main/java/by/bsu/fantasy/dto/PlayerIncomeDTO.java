package by.bsu.fantasy.dto;

import by.bsu.fantasy.util.IncomeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerIncomeDTO {
    private Long id;
    private IncomeType type;
    private String description;
    private Integer amount;

    private Long player_id;
}
