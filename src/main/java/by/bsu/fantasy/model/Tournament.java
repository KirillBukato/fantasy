package by.bsu.fantasy.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDateTime lockDateTime;
    private LocalDateTime unlockDateTime;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Team> teams;
}
