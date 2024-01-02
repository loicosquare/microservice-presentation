package com.tech.categoryService.controller;

import com.tech.categoryService.common.Constant.ConstantUrl;
import com.tech.categoryService.common.exception.ExceptionHandling;
import com.tech.categoryService.common.exception.category.CategoryAlreadyExistException;
import com.tech.categoryService.common.exception.category.CategoryNotFoundException;
import com.tech.categoryService.common.exception.game.GameExistException;
import com.tech.categoryService.common.exception.game.GameNotFoundException;
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
@RequestMapping(value = ConstantUrl.CATEGORIES)
public class CategoryController extends ExceptionHandling {

    private final CategoryService categoryService;

    @PostMapping(value = ConstantUrl.CREATE_CATEGORY)
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws CategoryAlreadyExistException {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping(value = ConstantUrl.UPDATE_CATEGORY)
    public ResponseEntity<Category> updateCategory(@PathVariable String categoryId, @RequestBody Category category) throws CategoryNotFoundException {
        category.setCategoryId(categoryId);
        Category updatedCategory = categoryService.updateCategory(category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @GetMapping(value = ConstantUrl.GET_ONE_CATEGORY)
    public ResponseEntity<Category> getCategoryById(@PathVariable String categoryId) throws CategoryNotFoundException {
        Category category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(value = ConstantUrl.GET_ALL_CATEGORIES)
    public ResponseEntity<List<Category>> getAllCategories() throws CategoryNotFoundException {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping(value = ConstantUrl.DELETE_CATEGORY)
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryId) throws CategoryNotFoundException {
        String result = categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = ConstantUrl.GET_GAME_BY_CATEGORY)
    public ResponseEntity<List<Game>> getGamesByCategoryId(@PathVariable String categoryId) throws GameNotFoundException {
        List<Game> games = categoryService.getGamesByCategoryId(categoryId);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PostMapping(value = ConstantUrl.ADD_GAME_TO_CATEGORY)
    public ResponseEntity<Category> addGameToCategory(@PathVariable String categoryId, @RequestBody Game game) throws GameExistException {
        Category category = categoryService.addGameToCategory(categoryId, game);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}/games/{gameId}")
    public ResponseEntity<Category> removeGameFromCategory(@PathVariable String categoryId, @PathVariable String gameId) throws CategoryNotFoundException {
        Category category = categoryService.removeGameFromCategory(categoryId, gameId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}

