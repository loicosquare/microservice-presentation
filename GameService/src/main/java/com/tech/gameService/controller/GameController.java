package com.tech.gameService.controller;

import com.tech.ServiceRegistry.common.entities.HttpResponse;
import com.tech.ServiceRegistry.common.exception.ExceptionHandling;
import com.tech.ServiceRegistry.common.Constant.ConstantUrl;
import com.tech.ServiceRegistry.common.exception.game.GameExistException;
import com.tech.ServiceRegistry.common.exception.game.GameNotFoundException;
import com.tech.gameService.entities.Game;
import com.tech.gameService.services.GameService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = ConstantUrl.GAMES)
public class GameController extends ExceptionHandling {

    private final GameService gameService;
    private static final String DELETE_MESSAGE = "Le jeu a été supprimé avec succès.";

    @GetMapping(value = {ConstantUrl.GET_ALL_GAME})
    public ResponseEntity<HttpResponse<Game>> getAllGames() throws GameNotFoundException {
        HttpResponse<Game> allGames = gameService.getAllGames();
        if (allGames != null && !allGames.getGames().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(allGames);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value ={ConstantUrl.GET_ONE_GAME})
    public ResponseEntity<HttpResponse<Game>> getOneGame(@RequestParam String gameId) {
        HttpResponse<Game> foundedGame = gameService.getGameById(gameId);
        if (foundedGame != null){
            return ResponseEntity.status(HttpStatus.OK).body(foundedGame);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = {ConstantUrl.CREATE_GAME})
    public ResponseEntity<HttpResponse<Game>> createGame(@RequestBody Game game) throws GameExistException {
        HttpResponse<Game> createdGame = gameService.createGame(game);
        if (createdGame != null){
            //return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
            return ResponseEntity.created(
                    URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/games/all").toUriString())
            ).body(createdGame);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = {ConstantUrl.UPDATE_GAME})
    public ResponseEntity<HttpResponse<Game>> updateGame(@RequestBody Game game) throws GameNotFoundException {
        HttpResponse<Game> updatedGame = gameService.updateGame(game);
        if (updatedGame != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedGame);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = {ConstantUrl.DELETE_GAME})
    public ResponseEntity<HttpResponse<Game>> deleteGame(@RequestParam String gameId) throws GameNotFoundException {
        if (gameService.deleteGame(gameId) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(gameService.deleteGame(gameId));
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping("/error")
    public ResponseEntity<HttpResponse<?>> handleError(HttpServletRequest request) {
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .reason("There is no mapping for a " + request.getMethod() + " request for this path on the server")
                        .message("There is no mapping for a " + request.getMethod() + " request for this path on the server")
                        .httpStatus(NOT_FOUND)
                        .httpStatusCode(NOT_FOUND.value())
                        .reason(HttpStatus.OK.getReasonPhrase())
                        .timeStamp(new Date())
                        .build(), NOT_FOUND
        );
    }
}
