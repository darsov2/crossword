package mk.ukim.finki.crosswordapi.service;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.crosswordapi.model.Crossword;
import mk.ukim.finki.crosswordapi.model.enums.Difficulty;
import mk.ukim.finki.crosswordapi.repository.CrosswordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DummyDataLoader {
    private final CrosswordRepository crosswordRepository;
    private final Logger logger = LoggerFactory.getLogger(DummyDataLoader.class);

    public DummyDataLoader(CrosswordRepository crosswordRepository) {
        this.crosswordRepository = crosswordRepository;
    }

    @PostConstruct
    public void init() {
        if(crosswordRepository.findByDateAssigned(LocalDate.now()).isEmpty()) {
            Crossword crossword1 = new Crossword();
            crossword1.setDateAssigned(LocalDate.now());
            crossword1.setDifficulty(Difficulty.MEDIUM);
            crossword1.setWidth(50);
            crossword1.setHeight(50);
            crossword1.setWordCount(100);
            crosswordRepository.save(crossword1);
            logger.info("Inserted Crossword for date {}", crossword1.getDateAssigned());
        }
    }
}
