package mk.ukim.finki.crosswordapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GuessedWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private WordInCrossword word;
    @ManyToOne
    private CrosswordGame crosswordGame;
    String guess;
    Boolean isCorrect;
}
