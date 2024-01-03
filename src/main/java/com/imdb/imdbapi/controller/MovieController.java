package com.imdb.imdbapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.imdbapi.dto.MovieRequest;
import com.imdb.imdbapi.dto.MovieResponse;
import com.imdb.imdbapi.service.MovieService;
import com.imdb.imdbapi.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class MovieController {

	@Autowired
	private MovieService service;
	
	@PostMapping("/movies")
	public ResponseEntity<ResponseStructure<MovieResponse>> addMovie(@RequestBody @Valid MovieRequest movieRequest) {
		return service.addMovie(movieRequest);
	}

	@GetMapping("/movies/{movieId}")
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieById(@PathVariable int movieId) {
		return service.findMovieById(movieId);
	}

	@GetMapping("movies/movieNames/{movieName}")
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieByName(@PathVariable String movieName) {
		return service.findMovieByName(movieName);
	}

	@PutMapping("/movies/{movieId}")
	public ResponseEntity<ResponseStructure<MovieResponse>> updateMovieById(@RequestBody @Valid MovieRequest request,@PathVariable int movieId) {
		return service.updateMovieById(request, movieId);
	}

	@DeleteMapping("/movies/{movieId}")
	public ResponseEntity<ResponseStructure<MovieResponse>> deleteMovieById(@PathVariable int movieId) {
		return service.deleteMovieById(movieId);
	}

	@GetMapping("/movies")
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findAllMovies() {
		return service.findAllMovies();
	}

	@GetMapping("/movies/genres/{movieGenres}")
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findMoviesByGenres(@PathVariable String movieGenres) {
		return service.findMoviesByGenres(movieGenres);
	}
	
}
