package mk.ukim.finki.crosswordapi.web.mapper;

import mk.ukim.finki.crosswordapi.model.Crossword;
import mk.ukim.finki.crosswordapi.service.CrosswordService;
import mk.ukim.finki.crosswordapi.web.response.TodaysCrosswordResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrosswordMapper {
    private final CrosswordService crosswordService;

    public CrosswordMapper(CrosswordService crosswordService) {
        this.crosswordService = crosswordService;
    }

    public TodaysCrosswordResponse getTodaysCrossword() {
        return toResponse(crosswordService.getTodaysCrossword());
    }

    public List<TodaysCrosswordResponse> getPreviousCrosswords() {
        return crosswordService.getPreviousCrosswords().stream().map(CrosswordMapper::toResponse).toList();
    }

    public static TodaysCrosswordResponse toResponse(Crossword crossword) {
        return new TodaysCrosswordResponse(crossword.getId(), crossword.getDateAssigned().toString(), crossword.getDifficulty().prettyName(),
                crossword.getWidth(), crossword.getHeight());
    }
}
