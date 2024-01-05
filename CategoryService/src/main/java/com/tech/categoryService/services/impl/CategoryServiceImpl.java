package com.tech.categoryService.services.impl;

import com.tech.categoryService.common.entities.HttpResponse;
import com.tech.categoryService.common.exception.category.CategoryAlreadyExistException;
import com.tech.categoryService.common.exception.category.CategoryNotFoundException;
import com.tech.categoryService.common.exception.game.GameExistException;
import com.tech.categoryService.common.exception.game.GameNotFoundException;
import com.tech.categoryService.entities.Category;
import com.tech.categoryService.external.entities.Game;
import com.tech.categoryService.external.services.GameService;
import com.tech.categoryService.repository.CategoryRepository;
import com.tech.categoryService.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.tech.categoryService.common.Constant.ConstantUrl.TEMP_IMAGE_BASE_URL;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;
    private final GameService gameService;
    private final RestTemplate restTemplate;

    /**
     * Crée une nouvelle catégorie.
     *
     * @param category La catégorie à créer.
     * @return La catégorie créée.
     * @throws CategoryAlreadyExistException si une catégorie est trouvée.
     */
    @Override
    public Category createCategory(Category category) throws CategoryAlreadyExistException {
        try {
            Category existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
            if (existingCategory != null) {
                throw new CategoryAlreadyExistException("La catégorie " + category.getCategoryName() + " existe déjà.");
            }else{
                category.setImageUrl(String.valueOf(new URL(TEMP_IMAGE_BASE_URL + category.getCategoryName().trim())));
            }
            return categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Erreur lors de la création de la catégorie : {}", e.getMessage());
            throw new CategoryAlreadyExistException("Erreur lors de la création de la catégorie {}." + e.getMessage());
        }
    }

    /**
     * Met à jour une catégorie existante.
     *
     * @param category La catégorie à mettre à jour.
     * @return La catégorie mise à jour.
     * @throws CategoryNotFoundException si aucune catégorie n'est trouvée.
     */
    @Override
    public Category updateCategory(Category category) throws CategoryNotFoundException {
        try {
            Category existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
            if (existingCategory != null) {
                throw new CategoryAlreadyExistException("La catégorie " + category.getCategoryName() + " existe déjà.");
            }
            return categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour de la catégorie : {}", e.getMessage());
            throw new CategoryNotFoundException("Erreur lors de la mise à jour de la catégorie {}."+ e.getMessage());
        }
    }

    /**
     * Obtient une catégorie par son ID.
     *
     * @param categoryId L'ID de la catégorie.
     * @return La catégorie correspondant à l'ID spécifié.
     * @throws CategoryNotFoundException si aucune catégorie n'est trouvée.
     */
    @Override
    public Category getCategoryById(String categoryId) throws CategoryNotFoundException, GameNotFoundException {
        try {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CategoryNotFoundException("Catégorie introuvable avec l'ID : " + categoryId));
            logger.info("Category : {}",  category);

            ResponseEntity<HttpResponse<Game>> gamesEntity = gameService.getAllGames();
            HttpResponse<Game> response = gamesEntity.getBody();
            logger.info("Game : {}", response);
            if(response != null) {
                logger.info("Game : " + response.getCategories());
                for (Game game : response.getCategories()) { // En principe getCategories retourne les games car c'est juste le nom générique utilisés dans le HttpResponse de CATEGORIE-SERVICE
                    if(game.getCategoryId().equals(category.getCategoryId())) { //Je n'utilise pas categoryId passé en param pour etre sure d'utilise ce qu'on a fetch by id;
                        category.setGames(List.of(game)); // je n'utilise par SilgeltonList car on peut avoir plusieurs jeux dans la categorie.
                    }
                }
            }
            logger.info("Category with games : {}", category);
            return category;
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de la catégorie par ID {}", e.getMessage());
            throw new CategoryNotFoundException("Erreur lors de la récupération de la catégorie par l'ID {}."+ categoryId);
        }
    }

    /**
     * Obtient toutes les catégories.
     *
     * @return List<Category> La liste de toutes les catégories.
     * @throws CategoryNotFoundException si aucune catégorie n'est trouvée.
     */
    @Override
    public List<Category> getAllCategories() throws CategoryNotFoundException {
        try {
            List<Category> categories = categoryRepository.findAll().stream()
                    .filter(category -> category.getCategoryName() != null)
                    .toList();
            logger.info("Categories : {}",  categories);

            /*ResponseEntity<HttpResponse<Game>> gamesEntity = gameService.getAllGames();
            HttpResponse<Game> response = gamesEntity.getBody();
            logger.info("Game : {}", response);*/

            ResponseEntity<Game[]> response = restTemplate.exchange(
                    "http://GAME-SERVICE/games/all/",
                    HttpMethod.GET,
                    null,
                    Game[].class
            );
            Game[] games = response.getBody();
            logger.info("Game : {}", response);

            if(games != null) {
                //logger.info("Game {} : ", response);
                for (Game game : games) { // En principe getCategories retourne les games car c'est juste le nom générique utilisés dans le HttpResponse de CATEGORIE-SERVICE
                    for(Category category : categories){
                        if(game.getCategoryId().equals(category.getCategoryId())) {
                            category.setGames(List.of(game));
                        }
                    }
                }
            }
            logger.info("Categories with games : {}", categories);
            return categories;
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de toutes les catégories : {}", e.getMessage());
            throw new CategoryNotFoundException("Erreur lors de la récupération de toutes les catégories {}."+ e.getMessage());
        }
    }

    /**
     * Supprime une catégorie par son ID.
     *
     * @param categoryId L'ID de la catégorie à supprimer.
     * @return Un message indiquant que la catégorie a été supprimée avec succès.
     * @throws CategoryNotFoundException si aucune catégorie n'est trouvée.
     */
    @Override
    public String deleteCategoryById(String categoryId) throws CategoryNotFoundException {
        try {
            categoryRepository.deleteById(categoryId);
            return "La catégorie avec l'ID : " + categoryId + " a été supprimée avec succès.";
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de la catégorie : {}", e.getMessage());
            throw new CategoryNotFoundException("Erreur lors de la suppression de la catégorie {}."+ e.getMessage());
        }
    }

    /**
     * Obtient la liste des jeux associés à une catégorie par son ID.
     *
     * @param categoryId L'ID de la catégorie.
     * @return List<Game> La liste des jeux associés à la catégorie.
     * @throws GameNotFoundException si aucun Game n'est trouvé.
     */
    @Override
    public List<Game> getGamesByCategoryId(String categoryId) throws GameNotFoundException {
        try {
            Category category = getCategoryById(categoryId);
            return category.getGames();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des jeux de la catégorie : {}", e.getMessage());
            throw new GameNotFoundException("Erreur lors de la récupération des jeux de la catégorie."+ e.getMessage());
        }
    }

    /**
     * Ajoute un jeu à une catégorie spécifiée.
     *
     * @param categoryId L'ID de la catégorie.
     * @param game Le jeu à ajouter.
     * @return La catégorie avec le jeu ajouté.
     */
    @Override
    public Category addGameToCategory(String categoryId, Game game) throws GameExistException {
        try {
            Category category = getCategoryById(categoryId);
            category.getGames().add(game);
            return categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout du jeu à la catégorie : {}", e.getMessage());
            throw new GameExistException("Erreur lors de l'ajout du jeu à la catégorie {}." + e.getMessage());
        }
    }

    /**
     * Supprime un jeu d'une catégorie spécifiée.
     *
     * @param categoryId L'ID de la catégorie.
     * @param gameId     L'ID du jeu à supprimer.
     * @return Category : La catégorie mise à jour après la suppression du jeu.
     */
    @Override
    public Category removeGameFromCategory(String categoryId, String gameId) throws CategoryNotFoundException {
        try {
            Category category = getCategoryById(categoryId);
            category.getGames().removeIf(game -> game.getGameId().equals(gameId));
            return categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du jeu de la catégorie : {}", e.getMessage());
            throw new CategoryNotFoundException("Erreur lors de la suppression du jeu de la catégorie."+ e);
        }
    }
}
