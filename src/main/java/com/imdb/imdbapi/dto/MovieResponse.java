package com.imdb.imdbapi.dto;
import java.time.LocalTime;
import java.util.Map;

import com.imdb.imdbapi.enums.Genres;
import com.imdb.imdbapi.enums.Languages;

public class MovieResponse {
	private int movieId;
	private String movieName;
	private Genres movieGenres;
	private Languages movieLanguage;
	private LocalTime movieDuration;
	private Double movieRating;
	
	private Map<String, String> options;

	public Double getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(Double movieRating) {
		this.movieRating = movieRating;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

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

	public Genres getMovieGenres() {
		return movieGenres;
	}

	public void setMovieGenres(Genres movieGenres) {
		this.movieGenres = movieGenres;
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

}
