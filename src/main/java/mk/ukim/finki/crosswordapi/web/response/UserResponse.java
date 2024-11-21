package mk.ukim.finki.crosswordapi.web.response;

import lombok.Data;

@Data
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    String username;
}
