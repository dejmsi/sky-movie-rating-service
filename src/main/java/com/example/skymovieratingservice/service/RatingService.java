package com.example.skymovieratingservice.service;

import com.example.skymovieratingservice.dto.RatingRequest;
import com.example.skymovieratingservice.dto.RatingResponse;
import com.example.skymovieratingservice.entity.Movie;
import com.example.skymovieratingservice.entity.Rating;
import com.example.skymovieratingservice.entity.User;
import com.example.skymovieratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    public RatingResponse addOrUpdateRating(String userEmail, Long movieId, RatingRequest request) {
        User user = userService.findByEmail(userEmail);
        Movie movie = movieService.findById(movieId);

        Optional<Rating> existingRating = ratingRepository.findByUserIdAndMovieId(user.getId(), movieId);

        Rating rating;
        if (existingRating.isPresent()) {
            // Update existing rating
            rating = existingRating.get();
            rating.setValue(request.getValue());
            rating.setComment(request.getComment());
        } else {
            // Create new rating
            rating = new Rating(request.getValue(), request.getComment(), user, movie);
        }

        rating = ratingRepository.save(rating);
        return new RatingResponse(rating);
    }

    public void deleteRating(String userEmail, Long movieId) {
        User user = userService.findByEmail(userEmail);
        Rating rating = ratingRepository.findByUserIdAndMovieId(user.getId(), movieId)
                .orElseThrow(() -> new RuntimeException("Rating not found"));

        ratingRepository.delete(rating);
    }

    public Optional<RatingResponse> getUserRatingForMovie(String userEmail, Long movieId) {
        User user = userService.findByEmail(userEmail);
        return ratingRepository.findByUserIdAndMovieId(user.getId(), movieId)
                .map(RatingResponse::new);
    }
}
