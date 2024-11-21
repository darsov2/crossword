package mk.ukim.finki.crosswordapi.service.auth;

import mk.ukim.finki.crosswordapi.model.CrosswordUser;
import mk.ukim.finki.crosswordapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CrosswordUser crosswordUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        logger.info("Loaded user: {}", crosswordUser.getUsername());
        return new User(username, crosswordUser.getPassword(), crosswordUser.getAuthorities());
    }
}
