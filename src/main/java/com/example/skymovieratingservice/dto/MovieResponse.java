package com.example.skymovieratingservice.dto;


import com.example.skymovieratingservice.entity.Movie;

public class MovieResponse {
    private Long id;
    private String name;
    private String description;
    private String genre;
    private Integer releaseYear;
    private Double averageRating;
    private Long ratingsCount;

    // Constructor from entity
    public MovieResponse(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.genre = movie.getGenre();
        this.releaseYear = movie.getReleaseYear();
    }

    // Constructor with rating stats
    public MovieResponse(Movie movie, Double averageRating, Long ratingsCount) {
        this(movie);
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }

    public Long getRatingsCount() { return ratingsCount; }
    public void setRatingsCount(Long ratingsCount) { this.ratingsCount = ratingsCount; }
}
