package com.tech.categoryService.entities;

import com.tech.categoryService.external.entities.Game;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("categories")
public class Category {
    @Id
    @NonNull
    private String categoryId;
    private String categoryName;
    private String categoryDescription;
    private String imageUrl;
    private List<Game> games = new ArrayList<>();
}
