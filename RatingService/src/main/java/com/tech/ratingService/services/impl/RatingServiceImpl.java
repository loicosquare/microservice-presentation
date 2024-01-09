package com.tech.ratingService.services.impl;

import com.tech.ratingService.entities.Rating;
import com.tech.ratingService.repository.RatingRepository;
import com.tech.ratingService.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    /**
     * @param rating
     * @return Rating : Created rating
     */
    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * @return
     * List<Rating> : List of all ratings.
     */
    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll().stream().toList();
    }

    /**
     * @param userId
     * @return List<Rating> : List of ratings for a given user id.
     */
    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    /**
     * @param enterpriseId
     * @return List<Rating> : List of ratings for a given enterpriseid.
     */
    @Override
    public List<Rating> getRatingByEnterpriseId(String enterpriseId) {
        return ratingRepository.findByEnterpriseId(enterpriseId);
    }

    /**
     * @param gameId
     * @return List<Rating> : List of ratings for a given game id.
     */
    @Override
    public List<Rating> getRatingByGameId(String gameId) {
        return ratingRepository.findByGameId(gameId);
    }
}
