package mk.ukim.finki.crosswordapi.web.request;

import lombok.Data;

import java.util.List;

@Data
public class CrosswordGameSubmission {
    Long crosswordGameId;
    List<CrosswordGridSubmission> answers;

    @Data
    public static class CrosswordGridSubmission {
        String answer;
        Long wordInCrosswordId;
    }

}
