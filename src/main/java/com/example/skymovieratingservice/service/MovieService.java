package com.example.skymovieratingservice.service;

import com.example.skymovieratingservice.dto.MovieResponse;
import com.example.skymovieratingservice.entity.Movie;
import com.example.skymovieratingservice.repository.MovieRepository;
import com.example.skymovieratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(movie -> {
                    Double avgRating = ratingRepository.findAverageRatingByMovieId(movie.getId()).orElse(0.0);
                    Long ratingsCount = ratingRepository.countRatingsByMovieId(movie.getId());
                    return new MovieResponse(movie, avgRating, ratingsCount);
                })
                .collect(Collectors.toList());
    }

    public MovieResponse getTopRatedMovie() {
        Movie topMovie = movieRepository.findTopRatedMovie()
                .orElseThrow(() -> new RuntimeException("No movies found"));

        Double avgRating = ratingRepository.findAverageRatingByMovieId(topMovie.getId()).orElse(0.0);
        Long ratingsCount = ratingRepository.countRatingsByMovieId(topMovie.getId());

        return new MovieResponse(topMovie, avgRating, ratingsCount);
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    public Movie createMovie(String name, String description, String genre, Integer releaseYear) {
        Movie movie = new Movie(name, description, genre, releaseYear);
        return movieRepository.save(movie);
    }
}

