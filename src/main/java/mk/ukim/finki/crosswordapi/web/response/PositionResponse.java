package mk.ukim.finki.crosswordapi.web.response;

import lombok.Data;

@Data
public class PositionResponse {
    Integer x;
    Integer y;
    boolean correct = false;
    char guess;

    public PositionResponse(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public PositionResponse(Integer x, Integer y, boolean correct, char guess) {
        this.x = x;
        this.y = y;
        this.correct = correct;
        this.guess = guess;
    }

}
