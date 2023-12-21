package com.tech.gameService.services;

import com.tech.ServiceRegistry.common.entities.HttpResponse;
import com.tech.ServiceRegistry.common.exception.game.GameExistException;
import com.tech.ServiceRegistry.common.exception.game.GameNotFoundException;
import com.tech.gameService.entities.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {
    HttpResponse<Game> createGame(Game game) throws GameExistException;
    HttpResponse<Game> updateGame(Game game) throws GameNotFoundException;
    HttpResponse<Game> getGameById(String gameId);
    HttpResponse<Game> getAllGames() throws GameNotFoundException;
    HttpResponse<Game> deleteGame(String gameId) throws GameNotFoundException;
}
