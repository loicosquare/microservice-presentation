package com.tech.gameService.controller;

import com.tech.ServiceRegistry.common.exception.ExceptionHandling;
import com.tech.ServiceRegistry.common.Constant.ConstantUrl;
import com.tech.gameService.entities.Game;
import com.tech.gameService.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = ConstantUrl.GAMES)
public class GameController extends ExceptionHandling {

    private final GameService gameService;
    private static final String DELETE_MESSAGE = "Le jeu a été supprimé avec succès.";

    @GetMapping(value = {ConstantUrl.GET_ALL_GAME})
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> allGames = gameService.getAllGames();
        if (allGames != null && !allGames.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(allGames);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value ={ConstantUrl.GET_ONE_GAME})
    public ResponseEntity<Game> getOneGame(@RequestParam String gameId) {
        Game foundedGame = gameService.getGameById(gameId);
        if (foundedGame != null){
            return ResponseEntity.status(HttpStatus.OK).body(foundedGame);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = {ConstantUrl.CREATE_GAME})
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = gameService.createGame(game);
        if (createdGame != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = {ConstantUrl.UPDATE_GAME})
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        Game updatedGame = gameService.updateGame(game);
        if (updatedGame != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedGame);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = {ConstantUrl.DELETE_GAME})
    public ResponseEntity<String> deleteGame(@RequestParam String gameId) {
        boolean isDeleted = Boolean.parseBoolean(gameService.deleteGame(gameId));

        if (isDeleted) {
            return ResponseEntity.ok(DELETE_MESSAGE);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Impossible de trouver ou de supprimer le jeu avec l'ID : " + gameId);
        }
    }

}
