package com.tech.rating.service.RatingService.services.impl;

import com.tech.rating.service.RatingService.entities.Rating;
import com.tech.rating.service.RatingService.repository.RatingRepository;
import com.tech.rating.service.RatingService.services.RatingService;
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
     * @param hotelId
     * @return
     */
    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
