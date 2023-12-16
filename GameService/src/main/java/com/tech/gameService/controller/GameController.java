package com.tech.gameService.controller;

import com.tech.gameService.entities.Game;
import com.tech.gameService.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> allGames = gameService.getAllGames();
        if (allGames != null && !allGames.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(allGames);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

}
