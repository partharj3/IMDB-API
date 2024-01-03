package com.imdb.imdbapi.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.imdb.imdbapi.enums.Genres;
import com.imdb.imdbapi.enums.Languages;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	private String movieName;
	private Genres movieGenre;
	private Languages movieLanguage;
	private LocalTime movieDuration;
//	private Double movieRating;
	
	@OneToMany(mappedBy = "movie", fetch=FetchType.EAGER)
	private List<Review> movieReviewList = new ArrayList<Review>();

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Genres getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(Genres movieGenre) {
		this.movieGenre = movieGenre;
	}

	public Languages getMovieLanguage() {
		return movieLanguage;
	}

	public void setMovieLanguage(Languages movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	public LocalTime getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(LocalTime movieDuration) {
		this.movieDuration = movieDuration;
	}

//	public Double getMovieRating() {
//		return movieRating;
//	}
//
//	public void setMovieRating(Double movieRating) {
//		this.movieRating = movieRating;
//	}

	public List<Review> getMovieReviewList() {
		return movieReviewList;
	}

	public void setMovieReviewList(List<Review> movieReviewList) {
		this.movieReviewList = movieReviewList;
	}
}
