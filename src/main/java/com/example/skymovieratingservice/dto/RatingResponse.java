package com.example.skymovieratingservice.dto;


import com.example.skymovieratingservice.entity.Rating;

import java.time.LocalDateTime;

public class RatingResponse {
    private Long id;
    private Integer value;
    private String comment;
    private String userEmail;
    private String movieName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor from entity
    public RatingResponse(Rating rating) {
        this.id = rating.getId();
        this.value = rating.getValue();
        this.comment = rating.getComment();
        this.userEmail = rating.getUser().getEmail();
        this.movieName = rating.getMovie().getName();
        this.createdAt = rating.getCreatedAt();
        this.updatedAt = rating.getUpdatedAt();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
