package mk.ukim.finki.crosswordapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import mk.ukim.finki.crosswordapi.model.enums.Difficulty;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Crossword {

    @Id
    private Long id;
    private Integer height;
    private Integer width;
    private Integer wordCount;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @CreatedDate
    private LocalDateTime dateCreated;
    private Boolean active;
    private LocalDate dateAssigned;
}
