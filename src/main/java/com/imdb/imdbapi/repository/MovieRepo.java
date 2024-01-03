package com.imdb.imdbapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imdb.imdbapi.entity.Movie;
import com.imdb.imdbapi.enums.Genres;

public interface MovieRepo extends JpaRepository<Movie, Integer>{

	public Movie findMovieByMovieName(String name);
	
	@Query("SELECT m FROM Movie m WHERE m.movieGenre=?1")
	public List<Movie> findMovieByMovieGenre(Genres movieGenre);
	
}
