package mk.ukim.finki.crosswordapi.web.mapper;

import mk.ukim.finki.crosswordapi.model.Crossword;
import mk.ukim.finki.crosswordapi.model.WordInCrossword;
import mk.ukim.finki.crosswordapi.model.enums.ExtensionDirection;
import mk.ukim.finki.crosswordapi.service.CrosswordService;
import mk.ukim.finki.crosswordapi.web.response.CrosswordGridResponse;
import mk.ukim.finki.crosswordapi.web.response.CrosswordWordPlacementResponse;
import mk.ukim.finki.crosswordapi.web.response.PositionResponse;
import mk.ukim.finki.crosswordapi.web.response.TodaysCrosswordResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class CrosswordMapper {
    private final CrosswordService crosswordService;

    public CrosswordMapper(CrosswordService crosswordService) {
        this.crosswordService = crosswordService;
    }

    public TodaysCrosswordResponse getTodaysCrossword() {
        return toResponse(crosswordService.getTodaysCrossword());
    }

    public static TodaysCrosswordResponse toResponse(Crossword crossword) {
        return new TodaysCrosswordResponse(crossword.getDateAssigned().toString(), crossword.getDifficulty().prettyName(),
                crossword.getWidth(), crossword.getHeight());
    }

    public CrosswordGridResponse getTodaysCrosswordGrid() {
        Crossword crossword = crosswordService.getTodaysCrosswordWithWordsFetched();
        List<CrosswordWordPlacementResponse> wordsList =
                crossword.getWords().stream().map(word -> new CrosswordWordPlacementResponse(word.getXPosition(),
                        word.getYPosition(),
                        word.getWord().getDefinition(),
                        word.getExtensionDirection() == ExtensionDirection.HORIZONTAL ? "right" : "down",
                        mapWordToPositionsList(word)
                )).toList();
        return new CrosswordGridResponse(
                crossword.getWidth(),
                crossword.getHeight(),
                wordsList
        );
    }

    private List<PositionResponse> mapWordToPositionsList(WordInCrossword crosswordWord) {
        if (crosswordWord.getExtensionDirection() == ExtensionDirection.HORIZONTAL) {
            int startPosition = crosswordWord.getXPosition() + 1;
            return IntStream.range(startPosition, startPosition + crosswordWord.getLength())
                    .mapToObj(x -> new PositionResponse(x, crosswordWord.getYPosition())).toList();
        } else {
            int startPosition = crosswordWord.getYPosition() + 1;
            return IntStream.range(startPosition, startPosition + crosswordWord.getLength())
                    .mapToObj(y -> new PositionResponse(crosswordWord.getXPosition(), y)).toList();
        }
    }
}
