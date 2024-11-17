package by.bsu.fantasy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Player {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToMany(mappedBy = "players")
    private List<User> users_picked;

    //TODO как то надо хранить список полученных очков

    public Player(String name, Double price, Integer points) {
        this.name = name;
        this.price = price;
        this.points = points;
    }
}
