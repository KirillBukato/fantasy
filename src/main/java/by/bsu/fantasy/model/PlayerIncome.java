package by.bsu.fantasy.model;

import by.bsu.fantasy.util.IncomeType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PlayerIncome {
    @Id
    @GeneratedValue
    private Long id;
    private IncomeType type;
    private String description;
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
}
