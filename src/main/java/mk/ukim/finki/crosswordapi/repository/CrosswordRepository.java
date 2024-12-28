package mk.ukim.finki.crosswordapi.repository;

import mk.ukim.finki.crosswordapi.model.Crossword;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CrosswordRepository extends JpaRepository<Crossword, Long> {
    Optional<Crossword> findByDateAssigned(LocalDate dateAssigned);

    @EntityGraph(value = "crosswordWithWords" , type= EntityGraph.EntityGraphType.FETCH)
    Optional<Crossword> findByDateAssignedAndWordCountGreaterThan(LocalDate dateAssigned, Integer wordCount);
}
