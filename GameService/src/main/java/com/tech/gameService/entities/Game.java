package com.tech.gameService.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("games")
@Builder
public class Game {
    private String gameId;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String platform; //(PS5, PS4, PC).
    private String developer;
    private String publisher;
    //private String ratingId;
    private double price;
    private int gameLength;
    private String imageUrl;
    private String enterpriseId; // Référence à l'enterprise qui a créé le jeu.
    private int categoryId; // Référence à la catégorie à laquelle appartient le jeu.
}
