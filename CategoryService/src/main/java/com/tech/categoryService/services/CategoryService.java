package com.tech.categoryService.services;

import com.tech.categoryService.common.exception.category.CategoryAlreadyExistException;
import com.tech.categoryService.common.exception.category.CategoryNotFoundException;
import com.tech.categoryService.common.exception.game.GameExistException;
import com.tech.categoryService.common.exception.game.GameNotFoundException;
import com.tech.categoryService.entities.Category;
import com.tech.categoryService.external.entities.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category createCategory(Category category) throws CategoryAlreadyExistException;
    Category updateCategory(Category category) throws CategoryNotFoundException;
    Category getCategoryById(String categoryId) throws CategoryNotFoundException, GameNotFoundException;
    List<Category> getAllCategories() throws CategoryNotFoundException;
    String deleteCategoryById(String categoryId) throws CategoryNotFoundException;
    List<Game> getGamesByCategoryId(String categoryId) throws GameNotFoundException;
    Category addGameToCategory(String categoryId, Game game) throws GameExistException;
    Category removeGameFromCategory(String categoryId, String gameId) throws CategoryNotFoundException;
}