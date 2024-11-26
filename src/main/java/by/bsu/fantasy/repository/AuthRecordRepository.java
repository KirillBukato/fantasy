package by.bsu.fantasy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsu.fantasy.model.AuthRecord;

@Repository
public interface AuthRecordRepository extends JpaRepository<AuthRecord, Long> {
    Optional<AuthRecord> findByUsername(String username);
}