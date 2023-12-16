package com.tech.gameService.repository;

import com.tech.gameService.entities.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String>{
}
