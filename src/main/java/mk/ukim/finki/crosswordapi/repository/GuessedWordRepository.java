package mk.ukim.finki.crosswordapi.repository;

import mk.ukim.finki.crosswordapi.model.GuessedWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuessedWordRepository extends JpaRepository<GuessedWord, Long> {
}
