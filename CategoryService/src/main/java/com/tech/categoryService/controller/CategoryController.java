package com.tech.categoryService.controller;

import com.tech.categoryService.entities.Category;
import com.tech.categoryService.external.entities.Game;
import com.tech.categoryService.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable int categoryId, @RequestBody Category category) {
        category.setCategoryId(categoryId);
        Category updatedCategory = categoryService.updateCategory(category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
        String result = categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/games")
    public ResponseEntity<List<Game>> getGamesByCategoryId(@PathVariable int categoryId) {
        List<Game> games = categoryService.getGamesByCategoryId(categoryId);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PostMapping("/{categoryId}/games")
    public ResponseEntity<Category> addGameToCategory(@PathVariable int categoryId, @RequestBody Game game) {
        Category category = categoryService.addGameToCategory(categoryId, game);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}/games/{gameId}")
    public ResponseEntity<Category> removeGameFromCategory(@PathVariable int categoryId, @PathVariable int gameId) {
        Category category = categoryService.removeGameFromCategory(categoryId, gameId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}

