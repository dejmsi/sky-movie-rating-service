package com.example.skymovieratingservice.controller;


import com.example.skymovieratingservice.dto.RatingRequest;
import com.example.skymovieratingservice.dto.RatingResponse;
import com.example.skymovieratingservice.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/movie/{movieId}")
    public ResponseEntity<RatingResponse> addOrUpdateRating(
            @PathVariable Long movieId,
            @Valid @RequestBody RatingRequest request,
            Authentication authentication) {

        String userEmail = authentication.getName();
        RatingResponse response = ratingService.addOrUpdateRating(userEmail, movieId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<String> deleteRating(
            @PathVariable Long movieId,
            Authentication authentication) {

        String userEmail = authentication.getName();
        ratingService.deleteRating(userEmail, movieId);
        return ResponseEntity.ok("Rating deleted successfully");
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<RatingResponse> getUserRating(
            @PathVariable Long movieId,
            Authentication authentication) {

        String userEmail = authentication.getName();
        Optional<RatingResponse> rating = ratingService.getUserRatingForMovie(userEmail, movieId);

        return rating.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

