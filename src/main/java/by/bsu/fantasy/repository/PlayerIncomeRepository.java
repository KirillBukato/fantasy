package by.bsu.fantasy.repository;

import by.bsu.fantasy.model.PlayerIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerIncomeRepository extends JpaRepository<PlayerIncome, Long> {
}
