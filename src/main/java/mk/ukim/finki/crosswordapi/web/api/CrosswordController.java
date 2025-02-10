package mk.ukim.finki.crosswordapi.web.api;

import mk.ukim.finki.crosswordapi.web.mapper.CrosswordGameMapper;
import mk.ukim.finki.crosswordapi.web.mapper.CrosswordMapper;
import mk.ukim.finki.crosswordapi.web.request.CrosswordGameSubmission;
import mk.ukim.finki.crosswordapi.web.response.CrosswordStats;
import mk.ukim.finki.crosswordapi.web.response.TodaysCrosswordResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/crossword")
@CrossOrigin("http://localhost:5173")
public class CrosswordController {
    private final CrosswordMapper crosswordMapper;
    private final CrosswordGameMapper crosswordGameMapper;

    public CrosswordController(CrosswordMapper crosswordMapper, CrosswordGameMapper crosswordGameMapper) {
        this.crosswordMapper = crosswordMapper;
        this.crosswordGameMapper = crosswordGameMapper;

        System.out.println(IntStream.rangeClosed(1, 100).filter(x -> x % 3 == 0).peek(System.out::println).sum());
    }

    @GetMapping("todays")
    public ResponseEntity<?> getTodaysCrossword() {
        return ResponseEntity.ok(crosswordMapper.getTodaysCrossword());
    }

    @GetMapping("/todays/grid")
    public ResponseEntity<?> getTodaysCrosswordGrid() {
        return ResponseEntity.ok(crosswordGameMapper.startTodaysCrosswordGame());
    }

    @GetMapping("/start/{id}")
    public ResponseEntity<?> getTodaysCrosswordGrid(@PathVariable Long id) {
        return ResponseEntity.ok(crosswordGameMapper.startCrosswordGame(id));
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitCrosswordSolution(@RequestBody CrosswordGameSubmission submission) {
        crosswordGameMapper.submitCrosswordSolution(submission);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/previous")
    public List<TodaysCrosswordResponse> getPreviousCrosswords() {
        return crosswordMapper.getPreviousCrosswords();
    }

    @GetMapping("/stats")
    public List<CrosswordStats> getCrosswordStats(@RequestParam(required = false) Long id) {
        return crosswordGameMapper.getStatsForCrossword(id);
    }
}
