package com.imdb.imdbapi.dto;

import com.imdb.imdbapi.entity.Movie;
import com.imdb.imdbapi.entity.User;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewRequest {
	
	@NotBlank(message="Review Message cannot be Blank")
	@Size(max=1000, message="Not more than 1000 characters")
	private String reviewMessage;
	
	@NotNull(message="Review cannot be null")
	@DecimalMin(value="0.0", message="review should not be less than 0")
	@DecimalMax(value="10.0", message="review should not be more than 10")
	private double reviewRating;

	private User user;
	
	private Movie movie;

	public String getReviewMessage() {
		return reviewMessage;
	}

	public void setReviewMessage(String reviewMessage) {
		this.reviewMessage = reviewMessage;
	}

	public double getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(double reviewRating) {
		this.reviewRating = reviewRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
