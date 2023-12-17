package com.tech.ratingService.controller;

import com.tech.ratingService.entities.Rating;
import com.tech.ratingService.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    //create ratingService
    //@PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    //get all of user
    //@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    //get all of enterprises
    @GetMapping("/enterprises/{enterpriseId}")
    public ResponseEntity<List<Rating>> getRatingsEnterpriseId(@PathVariable String enterpriseId) {
        return ResponseEntity.ok(ratingService.getRatingByEnterpriseId(enterpriseId));
    }
}
