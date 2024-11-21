package mk.ukim.finki.crosswordapi.repository;

import mk.ukim.finki.crosswordapi.model.Crossword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrosswordRepository extends JpaRepository<Crossword, Long> {
}
