package com.example.skymovieratingservice.repository;

import com.example.skymovieratingservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m LEFT JOIN m.ratings r GROUP BY m ORDER BY AVG(r.value) DESC")
    List<Movie> findTopRatedMovies();

    @Query("SELECT m FROM Movie m LEFT JOIN m.ratings r GROUP BY m ORDER BY AVG(r.value) DESC LIMIT 1")
    Optional<Movie> findTopRatedMovie();

    List<Movie> findByNameContainingIgnoreCase(String name);
}

