package by.bsu.fantasy.repository;

import by.bsu.fantasy.model.Pick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickRepository extends JpaRepository<Pick, Long> {
}
