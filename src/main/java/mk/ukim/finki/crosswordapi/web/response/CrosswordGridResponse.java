package mk.ukim.finki.crosswordapi.web.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CrosswordGridResponse {
    Long crosswordGameId;
    Integer width;
    Integer height;
    List<CrosswordWordPlacementResponse> wordPlacements;
    Boolean isFinished = Boolean.TRUE;
    LocalDateTime startedAt;
    LocalDateTime finishedAt;

    public CrosswordGridResponse(Long crosswordGameId, Integer width, Integer height, List<CrosswordWordPlacementResponse> wordPlacements, LocalDateTime startedAt) {
        this.crosswordGameId = crosswordGameId;
        this.width = width;
        this.height = height;
        this.wordPlacements = wordPlacements;
        this.startedAt = startedAt;
    }

    public CrosswordGridResponse(Long crosswordGameId, Integer width, Integer height, List<CrosswordWordPlacementResponse> wordPlacements, Boolean isFinished, LocalDateTime startedAt, LocalDateTime finishedAt) {
        this.crosswordGameId = crosswordGameId;
        this.width = width;
        this.height = height;
        this.wordPlacements = wordPlacements;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.isFinished = isFinished;
    }
}
