package com.tech.categoryService.external.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String enterpriseId; // Référence à l'entreprise qui a créé le jeu.
    private String categoryId; // Référence à la catégorie à laquelle appartient le jeu.
    //@Transient //not to be stored in db
    private List<Rating> ratings=new ArrayList<>();
}
