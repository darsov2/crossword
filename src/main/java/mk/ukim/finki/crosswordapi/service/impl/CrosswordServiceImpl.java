package mk.ukim.finki.crosswordapi.service.impl;

import mk.ukim.finki.crosswordapi.model.Crossword;
import mk.ukim.finki.crosswordapi.repository.CrosswordRepository;
import mk.ukim.finki.crosswordapi.service.CrosswordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CrosswordServiceImpl implements CrosswordService {
    private final CrosswordRepository crosswordRepository;

    public CrosswordServiceImpl(CrosswordRepository crosswordRepository) {
        this.crosswordRepository = crosswordRepository;
    }

    @Override
    public Crossword getTodaysCrossword() {
        return crosswordRepository.findByDateAssigned(LocalDate.now()).orElseThrow(() -> {
            throw new RuntimeException("Crossword for date " + LocalDate.now() + " not found!");
        });
    }
}
