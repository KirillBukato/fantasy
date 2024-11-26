package by.bsu.fantasy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private Integer points;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @ManyToMany(mappedBy = "players")
    private List<Pick> picks;

    @OneToMany(mappedBy = "player")
    private List<PlayerIncome> incomes;
}
