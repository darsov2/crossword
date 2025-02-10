package mk.ukim.finki.crosswordapi.repository;

import java.util.List;
import mk.ukim.finki.crosswordapi.model.Crossword;
import mk.ukim.finki.crosswordapi.model.CrosswordGame;
import mk.ukim.finki.crosswordapi.model.CrosswordUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CrosswordGameRepository extends JpaRepository<CrosswordGame, Long> {
    Optional<CrosswordGame> findByCrosswordAndCrosswordUser(Crossword crossword, CrosswordUser crosswordUser);
    List<CrosswordGame> findAllByCrossword(Crossword crossword);
    List<CrosswordGame> findAllByCrosswordAndFinishedAtNotNull(Crossword crossword);
}
