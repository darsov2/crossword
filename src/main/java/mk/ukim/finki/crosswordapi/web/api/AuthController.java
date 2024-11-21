package mk.ukim.finki.crosswordapi.web.api;

import mk.ukim.finki.crosswordapi.web.mapper.CrosswordUserMapper;
import mk.ukim.finki.crosswordapi.web.request.UserRegistrationRequest;
import mk.ukim.finki.crosswordapi.web.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CrosswordUserMapper crosswordUserMapper;

    public AuthController(CrosswordUserMapper crosswordUserMapper) {
        this.crosswordUserMapper = crosswordUserMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegistrationRequest request) {
        return ResponseEntity.ok(crosswordUserMapper.registerUser(request));
    }
}
