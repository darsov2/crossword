package mk.ukim.finki.crosswordapi.service.impl;

import mk.ukim.finki.crosswordapi.model.Crossword;
import mk.ukim.finki.crosswordapi.repository.CrosswordRepository;
import mk.ukim.finki.crosswordapi.service.CrosswordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CrosswordServiceImpl implements CrosswordService {
    private final CrosswordRepository crosswordRepository;

    public CrosswordServiceImpl(CrosswordRepository crosswordRepository) {
        this.crosswordRepository = crosswordRepository;
    }

    @Override
    public Crossword getTodaysCrossword() {
        return crosswordRepository.findByDateAssigned(LocalDate.now()).orElseThrow(() -> new RuntimeException("Crossword for date " + LocalDate.now() + " not found!"));
    }

    @Override
    public List<Crossword> getPreviousCrosswords() {
        return crosswordRepository.findAllByDateAssignedBefore(LocalDate.now());
    }

    @Override
    public Crossword getTodaysCrosswordWithWordsFetched() {
        return crosswordRepository.findByDateAssignedAndWordCountGreaterThan(LocalDate.now(), 0).orElseThrow(() -> new RuntimeException("Crossword for date " + LocalDate.now() + " not found!"));
    }

    @Override
    public Crossword getCrosswordWithWordsFetched(Long id) {
        return crosswordRepository.findById(id).orElseThrow(() -> new RuntimeException("Crossword with ID " + LocalDate.now() + " not found!"));
    }

    @Override
    public Crossword getTodaysOrCrosswordById(Long id) {
        if(id == null) {
            return getTodaysCrossword();
        }
        return crosswordRepository.findById(id).orElseThrow(() -> new RuntimeException("Crossword with ID " + id + " not found!"));
    }


}
