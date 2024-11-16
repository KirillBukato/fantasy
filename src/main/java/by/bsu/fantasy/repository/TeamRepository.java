package by.bsu.fantasy.repository;

import by.bsu.fantasy.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
