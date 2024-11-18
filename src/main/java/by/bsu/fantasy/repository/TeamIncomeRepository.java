package by.bsu.fantasy.repository;

import by.bsu.fantasy.model.TeamIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamIncomeRepository extends JpaRepository<TeamIncome, Long> {
}
