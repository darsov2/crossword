package mk.ukim.finki.crosswordapi.web.response;

import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.crosswordapi.model.CrosswordGame;

@Data
@AllArgsConstructor
public class CrosswordStats {
  private String username;
  private Integer correctGuesses;
  private Integer totalWords;
  private Long timeTaken;

  public static CrosswordStats toResponse(CrosswordGame crosswordGame) {
    return new CrosswordStats(
        crosswordGame.getCrosswordUser().getUsername(),
        crosswordGame.getGuessedWords(),
        crosswordGame.getCrossword().getWordCount(),
        Duration.between(crosswordGame.getStartedAt(), crosswordGame.getFinishedAt()).toSeconds()
    );
  }
}
