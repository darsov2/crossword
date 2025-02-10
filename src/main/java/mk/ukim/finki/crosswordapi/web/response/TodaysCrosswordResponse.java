package mk.ukim.finki.crosswordapi.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodaysCrosswordResponse {
    Long id;
    String date;
    String difficulty;
    Integer width;
    Integer height;
}
