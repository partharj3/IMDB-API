package com.imdb.imdbapi.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.imdb.imdbapi.dto.MovieRequest;
import com.imdb.imdbapi.dto.MovieResponse;
import com.imdb.imdbapi.util.ResponseStructure;

public interface MovieService {
	
	public ResponseEntity<ResponseStructure<MovieResponse>> addMovie(MovieRequest movieRequest);
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieById(int movieId);
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieByName(String movieName);
	public ResponseEntity<ResponseStructure<MovieResponse>> updateMovieById(MovieRequest request,int movieID);
	public ResponseEntity<ResponseStructure<MovieResponse>> deleteMovieById(int movieId);
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findAllMovies();
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findMoviesByGenres(String movieGenres);
	
}
