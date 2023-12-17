package com.tech.ratingService.services;

import com.tech.ratingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get all by UserId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByEnterpriseId(String enterpriseId);

    //get all by gameId
    List<Rating> getRatingByGameId(String gameId);
}
