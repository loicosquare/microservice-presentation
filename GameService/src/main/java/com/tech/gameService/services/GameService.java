package com.tech.gameService.services;

import com.tech.gameService.entities.Game;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {
    Game createGame(Game game);
    Game updateGame(Game game);
    Game getGameById(String gameId);
    List<Game> getAllGames();
    String deleteGame(String gameId);
}
