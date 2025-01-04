package mk.ukim.finki.crosswordapi.web.response;

import lombok.Data;

import java.util.List;

@Data
public class CrosswordWordPlacementResponse {
    Integer x;
    Integer y;
    String clue;
    String direction;
    List<PositionResponse> cells;
    Long id;

    public CrosswordWordPlacementResponse(Integer x, Integer y, String clue, String direction, Long id, List<PositionResponse> cells) {
        this.x = x;
        this.y = y;
        this.clue = clue;
        this.direction = direction;
        this.cells = cells;
        this.id = id;
    }
}
