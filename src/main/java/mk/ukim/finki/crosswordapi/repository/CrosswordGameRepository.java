package mk.ukim.finki.crosswordapi.repository;

import mk.ukim.finki.crosswordapi.model.CrosswordGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrosswordGameRepository extends JpaRepository<CrosswordGame, Long> {
}
