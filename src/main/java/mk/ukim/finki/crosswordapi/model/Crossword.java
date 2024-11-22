package mk.ukim.finki.crosswordapi.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.crosswordapi.model.enums.Difficulty;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Crossword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer height;
    private Integer width;
    private Integer wordCount;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @CreatedDate
    private LocalDateTime dateCreated;
    private Boolean active = true;
    private LocalDate dateAssigned;
}
