package com.tech.gameService.services.impl;

import com.tech.gameService.entities.Game;
import com.tech.gameService.externalEntities.Rating;
import com.tech.gameService.repository.GameRepository;
import com.tech.gameService.services.GameService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    /**
     * @param game
     * @return Game : Created game
     */
    @Override
    public Game createGame(Game game) {
        try {
            return gameRepository.save(game);
        } catch (Exception e) {
            logger.error("Une erreur est survenue lors de la création du jeu : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la création du jeu.", e);
        }
    }

    /**
     * @param game
     * @return Game : Updated game
     */
    @Override
    public Game updateGame(Game game) {


        try {
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
        } catch (Exception e) {
            logger.error("Une erreur est survenue lors de la mise à jour du jeu : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la mise à jour du jeu.", e);
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
    public String deleteGame(String id) {
        try {
            Optional<Game> gameOptional = gameRepository.findById(id);

            if (gameOptional.isPresent()) {
                gameRepository.delete(gameOptional.get());
                return "Le jeu a été supprimé avec succès.";
            } else {
                return "Impossible de trouver le jeu avec l'ID : " + id;
            }
        } catch (Exception e) {
            logger.error("Une erreur est survenue lors de la suppression du jeu : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la suppression du jeu.", e);
        }
    }

    /**
     * @return List<Game> : All games in database (MongoDB) as a list.
     */
    @Override
    public List<Game> getAllGames() {

        List<Game> allGames = gameRepository.findAll().stream().toList();

        allGames.forEach(game -> {
            Rating[] foundedRating = restTemplate.getForObject("http://RATING-SERVICE/ratings/" + game.getGameId() , Rating[].class);

            if (foundedRating != null && foundedRating.length > 0){

            }
        });

        try {
            return gameRepository.findAll();
        } catch (Exception e) {
            logger.error("Une erreur est survenue lors de la récupération de tous les jeux : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la récupération de tous les jeux.", e);
        }
    }
}
