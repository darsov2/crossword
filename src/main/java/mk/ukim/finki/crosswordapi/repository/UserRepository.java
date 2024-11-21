package mk.ukim.finki.crosswordapi.repository;

import mk.ukim.finki.crosswordapi.model.CrosswordUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CrosswordUser, Long> {
    Optional<CrosswordUser> findByUsername(String username);
}
