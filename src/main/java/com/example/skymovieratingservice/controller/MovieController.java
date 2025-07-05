package com.example.skymovieratingservice.controller;

import com.example.skymovieratingservice.dto.MovieResponse;
import com.example.skymovieratingservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<MovieResponse> getTopRatedMovie() {
        MovieResponse topMovie = movieService.getTopRatedMovie();
        return ResponseEntity.ok(topMovie);
    }
}

