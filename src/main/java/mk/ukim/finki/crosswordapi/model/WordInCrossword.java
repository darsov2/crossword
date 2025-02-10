package mk.ukim.finki.crosswordapi.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.crosswordapi.model.enums.ExtensionDirection;

@Data
@Entity
@NamedEntityGraph(
        name = "wordInCrosswordWithWord",
        attributeNodes = {
                @NamedAttributeNode(value = "word")
        }
)
public class WordInCrossword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer xPosition;
    private Integer yPosition;
    private Integer length;
    private ExtensionDirection extensionDirection;
    @ManyToOne
    private Crossword crossword;
    @ManyToOne
    private Word word;
}
