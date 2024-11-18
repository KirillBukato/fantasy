package by.bsu.fantasy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private Integer points;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @ManyToMany(mappedBy = "teams")
    private List<User> users_picked;

    @OneToMany(mappedBy = "team")
    private List<TeamIncome> incomes;
}
