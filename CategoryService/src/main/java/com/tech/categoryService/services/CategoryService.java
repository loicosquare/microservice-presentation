package com.tech.categoryService.services;

import com.tech.categoryService.entities.Category;
import com.tech.categoryService.external.entities.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Category category);
    Category getCategoryById(int categoryId);
    List<Category> getAllCategories();
    String deleteCategoryById(int categoryId);
    List<Game> getGamesByCategoryId(int categoryId);
    Category addGameToCategory(int categoryId, Game game);
    Category removeGameFromCategory(int categoryId, int gameId);
}