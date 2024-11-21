package mk.ukim.finki.crosswordapi.repository;

import mk.ukim.finki.crosswordapi.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
