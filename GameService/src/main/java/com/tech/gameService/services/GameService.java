package com.tech.gameService.services;

import com.tech.gameService.entities.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameService {

    Game createGame(Game game);
    Game updateGame(Game game);
    Game getGameById(String id);
    String deleteGame(String id);
    List<Game> getAllGames();
}
