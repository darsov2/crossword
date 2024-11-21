package mk.ukim.finki.crosswordapi.web.request;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UserRegistrationRequest {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
}
