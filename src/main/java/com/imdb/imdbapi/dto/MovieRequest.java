package com.imdb.imdbapi.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.imdb.imdbapi.entity.Review;
import com.imdb.imdbapi.enums.Genres;
import com.imdb.imdbapi.enums.Languages;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class MovieRequest {
	
	@NotBlank(message="Movie name cannot be null")
	@Size(max=255, message="Movie name should not be more than 255 characters")
	private String movieName;
	
	@Enumerated(EnumType.STRING)
//	@Pattern(regexp = "[A-Z]*", message = "Genres should be in capital letters")
	@NotNull(message="Genre cannot be null, Please enter any one: ACTION,"
			+ "\nTHRILLER,\nROMANCE,\nCOMEDY,\nHORROR,\nDRAMA,\nADVENTURE,\nCRIME,\nBIOPIC")
	private Genres movieGenre;
	
//	@Enumerated(EnumType.STRING)
//	@Pattern(regexp = "[A-Z]*", message = "Language  should in capital letters,Please Enter one Language ENGLISH,\r\n"
//			+ "	HINDI,\r\n"
//			+ "	TELUGU,\r\n"
//			+ "	TAMIL,\r\n"
//			+ "	KANNADA,\r\n"
//			+ "	MALAYALAM")
	private Languages movieLanguages;
	
	@NotNull(message = "Movie duration cannot be null")
	@PastOrPresent(message = "Movie duration must be in the past or present")
	private LocalTime movieDuration;
	
	private List<Review> reviewList = new ArrayList<Review>();

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

	public Languages getMovieLanguages() {
		return movieLanguages;
	}

	public void setMovieLanguages(Languages movieLanguages) {
		this.movieLanguages = movieLanguages;
	}

	public LocalTime getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(LocalTime movieDuration) {
		this.movieDuration = movieDuration;
	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

}
