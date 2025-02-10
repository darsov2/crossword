package mk.ukim.finki.crosswordapi.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.crosswordapi.model.*;
import mk.ukim.finki.crosswordapi.repository.CrosswordGameRepository;
import mk.ukim.finki.crosswordapi.repository.GuessedWordRepository;
import mk.ukim.finki.crosswordapi.repository.WordInCrosswordRepository;
import mk.ukim.finki.crosswordapi.service.CrosswordGameService;
import mk.ukim.finki.crosswordapi.web.request.CrosswordGameSubmission;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CrosswordGameServiceImpl implements CrosswordGameService {
    private final CrosswordGameRepository crosswordGameRepository;
    private final WordInCrosswordRepository wordInCrosswordRepository;
    private final GuessedWordRepository guessedWordRepository;

    public CrosswordGameServiceImpl(CrosswordGameRepository crosswordGameRepository, WordInCrosswordRepository wordInCrosswordRepository, GuessedWordRepository guessedWordRepository) {
        this.crosswordGameRepository = crosswordGameRepository;
        this.wordInCrosswordRepository = wordInCrosswordRepository;
        this.guessedWordRepository = guessedWordRepository;
    }

    @Override
    public CrosswordGame createCrosswordGame(Crossword crossword, CrosswordUser user) {
        Optional<CrosswordGame> crosswordGameOptional = crosswordGameRepository
                .findByCrosswordAndCrosswordUser(crossword, user);

        if (crosswordGameOptional.isPresent()) {
            return crosswordGameOptional.get();
        } else {
            CrosswordGame crosswordGame = new CrosswordGame();
            crosswordGame.setCrossword(crossword);
            crosswordGame.setStartedAt(LocalDateTime.now());
            crosswordGame.setGuessedWords(0);
            crosswordGame.setCrosswordUser(user);
            crosswordGame.setShowErrors(true);

            return crosswordGameRepository.save(crosswordGame);
        }
    }

    @Override
    @Transactional
    public CrosswordGame submit(CrosswordGame crosswordGame, List<CrosswordGameSubmission.CrosswordGridSubmission> answers) {
        AtomicInteger counter = new AtomicInteger(0);

        List<GuessedWord> newGuessedWords = answers.stream()
                .map(answer -> {
                    GuessedWord guessedWord = new GuessedWord();
                    WordInCrossword wordInCrossword = wordInCrosswordRepository.findById(answer.getWordInCrosswordId()).orElseThrow();
                    guessedWord.setWord(wordInCrossword);
                    guessedWord.setGuess(answer.getAnswer());
                    guessedWord.setCrosswordGame(crosswordGame);
                    guessedWord.setIsCorrect(checkIfCorrect(answer.getAnswer(), wordInCrossword));
                    if(guessedWord.getIsCorrect()) {
                        counter.incrementAndGet();
                    }
                    return guessedWord;
                }).toList();

        guessedWordRepository.saveAll(newGuessedWords);

        crosswordGame.setGuessedWords(counter.get());
        crosswordGame.setFinishedAt(LocalDateTime.now());

        return crosswordGameRepository.save(crosswordGame);
    }

    @Override
    public CrosswordGame findById(Long id) {
        return crosswordGameRepository.findById(id).orElseThrow(() -> new RuntimeException("CrosswordGame with id " + id + " not found"));
    }

    @Override
    public List<CrosswordGame> findAllByCrossword(Crossword crossword) {
        return crosswordGameRepository.findAllByCrossword(crossword);
    }

    @Override
    public List<CrosswordGame> findAllFinishedByCrossword(Crossword crossword) {
        return crosswordGameRepository.findAllByCrosswordAndFinishedAtNotNull(crossword);
    }

    private Boolean checkIfCorrect(String answer, WordInCrossword wordInCrossword) {
        return answer.equalsIgnoreCase(wordInCrossword.getWord().getWord());
    }
}
