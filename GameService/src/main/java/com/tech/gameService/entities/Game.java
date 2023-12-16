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
    private String genre; //A voir si le genre va rester compte tenue de la présence de la catégorie dans la table jeu.
    private String platform; //Pour connaitre sur quelles plateformes (PS5, PS4, PC par exemple).
    private String developer;
    private String publisher;
    //private String ratingId;
    private double price;
    private int gameLength;
    private String imageUrl;
    private int categoryId; // Référence à la catégorie à laquelle appartient le jeu.
}
