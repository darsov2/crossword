package mk.ukim.finki.crosswordapi.service;

import mk.ukim.finki.crosswordapi.model.Crossword;
import mk.ukim.finki.crosswordapi.model.CrosswordGame;
import mk.ukim.finki.crosswordapi.model.CrosswordUser;
import mk.ukim.finki.crosswordapi.web.request.CrosswordGameSubmission;

import java.util.List;

public interface CrosswordGameService {
    CrosswordGame createCrosswordGame(Crossword crossword, CrosswordUser user);
    CrosswordGame submit(CrosswordGame crosswordGame, List<CrosswordGameSubmission.CrosswordGridSubmission> answers);

    CrosswordGame findById(Long id);
    List<CrosswordGame> findAllByCrossword(Crossword crossword);
    List<CrosswordGame> findAllFinishedByCrossword(Crossword crossword);
}
