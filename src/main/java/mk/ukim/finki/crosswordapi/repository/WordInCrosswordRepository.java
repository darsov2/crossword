package mk.ukim.finki.crosswordapi.repository;

import mk.ukim.finki.crosswordapi.model.WordInCrossword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordInCrosswordRepository extends JpaRepository<WordInCrossword, Long> {
}
