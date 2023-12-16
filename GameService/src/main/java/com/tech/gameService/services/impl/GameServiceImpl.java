package com.tech.gameService.services.impl;

import com.tech.gameService.entities.Game;
import com.tech.gameService.repository.GameRepository;
import com.tech.gameService.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    /**
     * @param game
     * @return Game : Created game
     */
    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    /**
     * @param game
     * @return Game : Updated game
     */
    @Override
    public Game updateGame(Game game) {
        Optional<Game> foundedGameOptional = gameRepository.findById(game.getGameId());

        if (foundedGameOptional.isPresent()) {
            Game foundedGame = foundedGameOptional.get();

            Game updatedGame = Game.builder()
                    .gameId(foundedGame.getGameId())
                    .genre(game.getGenre())
                    .title(game.getTitle())
                    .description(game.getDescription())
                    .releaseDate(game.getReleaseDate())
                    .platform(game.getPlatform())
                    .developer(game.getDeveloper())
                    .publisher(game.getPublisher())
                    //.ratingId(game.getRatingId())
                    .price(game.getPrice())
                    .gameLength(game.getGameLength())
                    .imageUrl(game.getImageUrl())
                    .categoryId(game.getCategoryId()) //TODO : Replace by category object comming from category service (Microservice)
                    .build();
            return gameRepository.save(updatedGame);
        } else {
            return null;
        }
    }

    /**
     * @param id
     * @return Game : Game by id
     */
    @Override
    public Game getGameById(String id) {
        return gameRepository.findAll().stream()
                .filter(game -> game.getGameId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Game with id {} not found"+ id));
    }

    /**
     * @param id
     */
    @Override
    public void deleteGame(String id) {
        Optional<Game> foundedGame = gameRepository.findAll().stream()
                .filter(game -> game.getGameId().equals(id))
                .findFirst();

        foundedGame.ifPresent(this.gameRepository::delete);
    }

    /**
     * @return List<Game> : All games in database (MongoDB) as a list.
     */
    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll().stream().toList();
    }
}
