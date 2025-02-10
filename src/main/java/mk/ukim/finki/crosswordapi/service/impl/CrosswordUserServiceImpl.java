package mk.ukim.finki.crosswordapi.service.impl;

import mk.ukim.finki.crosswordapi.model.CrosswordUser;
import mk.ukim.finki.crosswordapi.repository.UserRepository;
import mk.ukim.finki.crosswordapi.service.CrosswordUserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CrosswordUserServiceImpl implements CrosswordUserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CrosswordUserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CrosswordUser register(CrosswordUser crosswordUser) {
        crosswordUser.setPassword(passwordEncoder.encode(crosswordUser.getPassword()));
        return repository.save(crosswordUser);
    }

    @Override
    public CrosswordUser currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            return (CrosswordUser) authentication.getPrincipal();
        }
    }
}
