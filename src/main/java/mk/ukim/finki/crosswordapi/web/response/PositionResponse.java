package mk.ukim.finki.crosswordapi.web.response;

import lombok.Data;

@Data
public class PositionResponse {
    Integer x;
    Integer y;

    public PositionResponse(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
