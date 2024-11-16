package by.bsu.fantasy.repository;

import by.bsu.fantasy.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
