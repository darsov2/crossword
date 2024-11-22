package mk.ukim.finki.crosswordapi.web.api;

import mk.ukim.finki.crosswordapi.service.CrosswordService;
import mk.ukim.finki.crosswordapi.web.mapper.CrosswordMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
