package mk.ukim.finki.crosswordapi.web.api;

import mk.ukim.finki.crosswordapi.web.mapper.CrosswordMapper;
import mk.ukim.finki.crosswordapi.web.request.CrosswordGridSubmission;
import mk.ukim.finki.crosswordapi.web.response.CrosswordGridResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crossword")
@CrossOrigin("http://localhost:5173")
public class CrosswordController {
    private final CrosswordMapper crosswordMapper;

    public CrosswordController(CrosswordMapper crosswordMapper) {
        this.crosswordMapper = crosswordMapper;
    }

    @GetMapping("todays")
    public ResponseEntity<?> getTodaysCrossword() {
        return ResponseEntity.ok(crosswordMapper.getTodaysCrossword());
    }

    @GetMapping("/todays/grid")
    public ResponseEntity<?> getTodaysCrosswordGrid() {
        return ResponseEntity.ok(crosswordMapper.getTodaysCrosswordGrid());
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitCrosswordSolution(@RequestBody List<CrosswordGridSubmission> answers) {

    }
}
