package com.tech.categoryService.external.services;

import com.tech.categoryService.common.Constant.ConstantUrl;
import com.tech.categoryService.common.entities.HttpResponse;
import com.tech.categoryService.common.exception.game.GameNotFoundException;
import com.tech.categoryService.external.entities.Game;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "GAME-SERVICE")
public interface GameService {
    //@GetMapping(value = {ConstantUrl.GAMES + ConstantUrl.GET_ALL_GAME})
    @GetMapping(value = "/games/all")
    ResponseEntity<HttpResponse<Game>> getAllGames() throws GameNotFoundException;
}
