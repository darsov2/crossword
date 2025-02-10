package mk.ukim.finki.crosswordapi.repository;

import mk.ukim.finki.crosswordapi.model.WordInCrossword;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordInCrosswordRepository extends JpaRepository<WordInCrossword, Long> {
    @EntityGraph(value = "wordInCrosswordWithWord" , type= EntityGraph.EntityGraphType.FETCH)
    @Override
    Optional<WordInCrossword> findById(Long id);
}
