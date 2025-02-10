package mk.ukim.finki.crosswordapi.service;

import mk.ukim.finki.crosswordapi.model.CrosswordUser;

public interface CrosswordUserService {
    public CrosswordUser register(CrosswordUser crosswordUser);

    CrosswordUser currentUser();
}
