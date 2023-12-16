package com.tech.ratingService.services.impl;

import com.tech.ratingService.entities.Rating;
import com.tech.ratingService.repository.RatingRepository;
import com.tech.ratingService.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    /**
     * @param rating
     * @return
     */
    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * @return
     */
    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll().stream().toList();
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    /**
     * @param entrepriseId
     * @return
     */
    @Override
    public List<Rating> getRatingByEntrepriseId(String entrepriseId) {
        return ratingRepository.findByEntrepriseId(entrepriseId);
    }
}
