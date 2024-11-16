package by.bsu.fantasy.repository;

import by.bsu.fantasy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
