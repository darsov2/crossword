package mk.ukim.finki.crosswordapi.web.mapper;

import mk.ukim.finki.crosswordapi.model.CrosswordUser;
import mk.ukim.finki.crosswordapi.service.CrosswordUserService;
import mk.ukim.finki.crosswordapi.web.request.UserRegistrationRequest;
import mk.ukim.finki.crosswordapi.web.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class CrosswordUserMapper {

    private final CrosswordUserService service;

    public CrosswordUserMapper(CrosswordUserService service) {
        this.service = service;
    }

    public UserResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        CrosswordUser crosswordUser = new CrosswordUser();
        crosswordUser.setUsername(userRegistrationRequest.getUsername());
        crosswordUser.setPassword(userRegistrationRequest.getPassword());
        crosswordUser.setEmail(userRegistrationRequest.getEmail());
        crosswordUser.setFirstName(userRegistrationRequest.getFirstName());
        crosswordUser.setLastName(userRegistrationRequest.getLastName());
        return toResponse(service.register(crosswordUser));
    }

    public UserResponse status() {
        CrosswordUser crosswordUser = service.currentUser();
        if(crosswordUser == null) {
            return null;
        }
        return toResponse(service.currentUser());
    }

    public static UserResponse toResponse(CrosswordUser crosswordUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(crosswordUser.getUsername());
        userResponse.setEmail(crosswordUser.getEmail());
        userResponse.setId(crosswordUser.getId());
        userResponse.setFirstName(crosswordUser.getFirstName());
        userResponse.setLastName(crosswordUser.getLastName());
        return userResponse;
    }
}
