package mk.ukim.finki.crosswordapi.service;

import mk.ukim.finki.crosswordapi.model.Crossword;

import java.util.List;

public interface CrosswordService {
    Crossword getTodaysCrossword();
    List<Crossword> getPreviousCrosswords();

    Crossword getTodaysCrosswordWithWordsFetched();

    Crossword getCrosswordWithWordsFetched(Long id);
    Crossword getTodaysOrCrosswordById(Long id);
}
