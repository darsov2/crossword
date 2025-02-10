package mk.ukim.finki.crosswordapi.web.mapper;

import mk.ukim.finki.crosswordapi.model.*;
import mk.ukim.finki.crosswordapi.model.enums.ExtensionDirection;
import mk.ukim.finki.crosswordapi.service.CrosswordGameService;
import mk.ukim.finki.crosswordapi.service.CrosswordService;
import mk.ukim.finki.crosswordapi.service.CrosswordUserService;
import mk.ukim.finki.crosswordapi.web.request.CrosswordGameSubmission;
import mk.ukim.finki.crosswordapi.web.response.CrosswordGridResponse;
import mk.ukim.finki.crosswordapi.web.response.CrosswordStats;
import mk.ukim.finki.crosswordapi.web.response.CrosswordWordPlacementResponse;
import mk.ukim.finki.crosswordapi.web.response.PositionResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class CrosswordGameMapper {
    private final CrosswordGameService crosswordGameService;
    private final CrosswordUserService crosswordUserService;
    private final CrosswordService crosswordService;

    public CrosswordGameMapper(CrosswordGameService crosswordGameService, CrosswordUserService crosswordUserService, CrosswordService crosswordService) {
        this.crosswordGameService = crosswordGameService;
        this.crosswordUserService = crosswordUserService;
        this.crosswordService = crosswordService;
    }

    public void submitCrosswordSolution(CrosswordGameSubmission submission) {
        CrosswordGame crosswordGame = crosswordGameService.findById(submission.getCrosswordGameId());
        crosswordGame = crosswordGameService.submit(crosswordGame, submission.getAnswers());
    }

    public CrosswordGridResponse startCrosswordGame(Long id){
        CrosswordUser currentUser = crosswordUserService.currentUser();
        Crossword crossword = crosswordService.getCrosswordWithWordsFetched(id);
        return getCrosswordGridResponse(currentUser, crossword);
    }

    private CrosswordGridResponse getCrosswordGridResponse(CrosswordUser currentUser, Crossword crossword) {
        CrosswordGame crosswordGame = crosswordGameService.createCrosswordGame(crossword, currentUser);

        List<CrosswordWordPlacementResponse> wordsList =
                crossword.getWords().stream().map(word -> new CrosswordWordPlacementResponse(word.getXPosition(),
                        word.getYPosition(),
                        word.getWord().getDefinition(),
                        word.getExtensionDirection() == ExtensionDirection.HORIZONTAL ? "right" : "down",
                        word.getId(),
                        crosswordGame.getFinishedAt() == null ? mapWordToPositionsList(word, crosswordGame.getGuessedWordsList()) : mapWordToPositionsListFinished(word, crosswordGame.getGuessedWordsList())
                )).toList();
        return new CrosswordGridResponse(
                crosswordGame.getId(),
                crossword.getWidth(),
                crossword.getHeight(),
                wordsList,
                crosswordGame.getFinishedAt() != null,
                crosswordGame.getStartedAt(),
                crosswordGame.getFinishedAt()
        );
    }

    public CrosswordGridResponse startTodaysCrosswordGame() {
        CrosswordUser currentUser = crosswordUserService.currentUser();
        Crossword crossword = crosswordService.getTodaysCrosswordWithWordsFetched();
        return getCrosswordGridResponse(currentUser, crossword);
    }

    public List<CrosswordStats> getStatsForCrossword(Long id) {
        Crossword crossword = crosswordService.getTodaysOrCrosswordById(id);
        List<CrosswordGame> games = crosswordGameService.findAllFinishedByCrossword(crossword);
        return games.stream().map(CrosswordStats::toResponse).toList();
    }

    private List<PositionResponse> mapWordToPositionsListFinished(WordInCrossword crosswordWord, List<GuessedWord> guessedWords) {
        String guessedWord = guessedWords.stream()
                .filter(x -> x.getWord().getId().equals(crosswordWord.getId()))
                .findFirst()
                .map(GuessedWord::getGuess)
                .orElse(null);
        String word = crosswordWord.getWord().getWord();

        if (crosswordWord.getExtensionDirection() == ExtensionDirection.HORIZONTAL) {
            int startPosition = crosswordWord.getXPosition() + 1;
            return IntStream.range(startPosition, startPosition + crosswordWord.getLength())
                    .mapToObj(x -> new PositionResponse(x, crosswordWord.getYPosition(), checkPositionEquality(word, guessedWord, x - startPosition), getCharAtPosition(word, x - startPosition))).toList();
        } else {
            int startPosition = crosswordWord.getYPosition() + 1;
            return IntStream.range(startPosition, startPosition + crosswordWord.getLength())
                    .mapToObj(y -> new PositionResponse(crosswordWord.getXPosition(), y, checkPositionEquality(word, guessedWord, y - startPosition), getCharAtPosition(word, y - startPosition))).toList();
        }
    }

    private List<PositionResponse> mapWordToPositionsList(WordInCrossword crosswordWord, List<GuessedWord> guessedWords) {
        String guessedWord = guessedWords.stream()
                .filter(x -> x.getWord().getId().equals(crosswordWord.getId()))
                .findFirst()
                .map(GuessedWord::getGuess)
                .orElse(null);
        String word = crosswordWord.getWord().getWord();

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

    private boolean checkPositionEquality(String word, String guess, int position) {
        if (guess == null) {
            return false;
        }
        return guess.charAt(position) == word.charAt(position);
    }

    private char getCharAtPosition(String guess, int position) {
        if (guess == null) {
            return ' ';
        }
        return guess.charAt(position);
    }
}
