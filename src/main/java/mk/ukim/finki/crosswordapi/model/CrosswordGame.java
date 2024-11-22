package mk.ukim.finki.crosswordapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CrosswordGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Integer guessedWords;
    private Boolean showErrors;
    @ManyToOne
    private CrosswordUser crosswordUser;
    @ManyToOne
    private Crossword crossword;
}
