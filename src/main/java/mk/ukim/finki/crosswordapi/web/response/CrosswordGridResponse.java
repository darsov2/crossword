package mk.ukim.finki.crosswordapi.web.response;

import lombok.Data;

import java.util.List;

@Data
public class CrosswordGridResponse {
    Integer width;
    Integer height;
    List<CrosswordWordPlacementResponse> wordPlacements;

    public CrosswordGridResponse(Integer width, Integer height, List<CrosswordWordPlacementResponse> wordPlacements) {
        this.width = width;
        this.height = height;
        this.wordPlacements = wordPlacements;
    }
}
