package com.example.skymovieratingservice.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingRequest {
    @NotNull
    @Min(1)
    @Max(5)
    private Integer value;

    private String comment;

    // Constructors
    public RatingRequest() {}

    public RatingRequest(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    // Getters and Setters
    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
