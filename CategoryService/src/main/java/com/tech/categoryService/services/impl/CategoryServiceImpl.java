package com.tech.categoryService.services.impl;

import com.tech.categoryService.entities.Category;
import com.tech.categoryService.external.entities.Game;
import com.tech.categoryService.repository.CategoryRepository;
import com.tech.categoryService.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final CategoryRepository categoryRepository;

    /**
     * Crée une nouvelle catégorie.
     *
     * @param category La catégorie à créer.
     * @return La catégorie créée.
     */
    @Override
    public Category createCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Erreur lors de la création de la catégorie : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la création de la catégorie.", e);
        }
    }

    /**
     * Met à jour une catégorie existante.
     *
     * @param category La catégorie à mettre à jour.
     * @return La catégorie mise à jour.
     */
    @Override
    public Category updateCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour de la catégorie : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la mise à jour de la catégorie.", e);
        }
    }

    /**
     * Obtient une catégorie par son ID.
     *
     * @param categoryId L'ID de la catégorie.
     * @return La catégorie correspondant à l'ID spécifié.
     * @throws RuntimeException si aucune catégorie n'est trouvée.
     */
    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec l'ID : " + categoryId));
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de la catégorie par ID : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la récupération de la catégorie par ID.", e);
        }
    }

    /**
     * Obtient toutes les catégories.
     *
     * @return La liste de toutes les catégories.
     */
    @Override
    public List<Category> getAllCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de toutes les catégories : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la récupération de toutes les catégories.", e);
        }
    }

    /**
     * Supprime une catégorie par son ID.
     *
     * @param categoryId L'ID de la catégorie à supprimer.
     * @return Un message indiquant que la catégorie a été supprimée avec succès.
     */
    @Override
    public String deleteCategoryById(int categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
            return "La catégorie avec l'ID : " + categoryId + " a été supprimée avec succès.";
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de la catégorie : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la suppression de la catégorie.", e);
        }
    }

    /**
     * Obtient la liste des jeux associés à une catégorie par son ID.
     *
     * @param categoryId L'ID de la catégorie.
     * @return La liste des jeux associés à la catégorie.
     */
    @Override
    public List<Game> getGamesByCategoryId(int categoryId) {
        try {
            Category category = getCategoryById(categoryId);
            return category.getGames();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des jeux de la catégorie : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la récupération des jeux de la catégorie.", e);
        }
    }

    /**
     * Ajoute un jeu à une catégorie spécifiée.
     *
     * @param categoryId L'ID de la catégorie.
     * @param game       Le jeu à ajouter.
     * @return La catégorie avec le jeu ajouté.
     */
    @Override
    public Category addGameToCategory(int categoryId, Game game) {
        try {
            Category category = getCategoryById(categoryId);
            category.getGames().add(game);
            return categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout du jeu à la catégorie : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de l'ajout du jeu à la catégorie.", e);
        }
    }

    /**
     * Supprime un jeu d'une catégorie spécifiée.
     *
     * @param categoryId L'ID de la catégorie.
     * @param gameId     L'ID du jeu à supprimer.
     * @return La catégorie mise à jour après la suppression du jeu.
     */
    @Override
    public Category removeGameFromCategory(int categoryId, int gameId) {
        try {
            Category category = getCategoryById(categoryId);
            category.getGames().removeIf(game -> game.getGameId().equals(gameId));
            return categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du jeu de la catégorie : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la suppression du jeu de la catégorie.", e);
        }
    }
}
