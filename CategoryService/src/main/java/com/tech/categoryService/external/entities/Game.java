package com.tech.categoryService.external.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

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
    private String genre;
    private String platform;
    private String developer;
    private String publisher;
    //private String ratingId;
    private double price;
    private int gameLength;
    private String imageUrl;
    private String enterpriseId;
    private int categoryId;
}
