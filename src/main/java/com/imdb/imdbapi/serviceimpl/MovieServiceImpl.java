package com.imdb.imdbapi.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imdb.imdbapi.dto.MovieRequest;
import com.imdb.imdbapi.dto.MovieResponse;
import com.imdb.imdbapi.entity.Movie;
import com.imdb.imdbapi.enums.Genres;
import com.imdb.imdbapi.exception.MovieDataNotExistException;
import com.imdb.imdbapi.exception.MovieNotFoundByIdException;
import com.imdb.imdbapi.exception.MovieNotFoundByNameException;
import com.imdb.imdbapi.exception.RecordAlreadyExistException;
import com.imdb.imdbapi.repository.MovieRepo;
import com.imdb.imdbapi.repository.ReviewRepo;
import com.imdb.imdbapi.service.MovieService;
import com.imdb.imdbapi.util.ResponseStructure;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	private Movie getMovieObject(MovieRequest movieRequest) { 
		Movie movie = new Movie();
		movie.setMovieName(movieRequest.getMovieName());
		movie.setMovieGenre(movieRequest.getMovieGenre());
		movie.setMovieLanguage(movieRequest.getMovieLanguages());
		movie.setMovieDuration(movieRequest.getMovieDuration());
		movie.setMovieReviewList(movieRequest.getReviewList());
		return movie;
	}
	
	private MovieResponse getMovieResponse(Movie movie) { 
		MovieResponse response = new MovieResponse();
		response.setMovieId(movie.getMovieId());
		response.setMovieName(movie.getMovieName());
		response.setMovieGenres(movie.getMovieGenre());
		response.setMovieLanguage(movie.getMovieLanguage());
		response.setMovieDuration(movie.getMovieDuration());
		return response;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> addMovie(MovieRequest movieRequest) {
		Movie movie = movieRepo.findMovieByMovieName(movieRequest.getMovieName());
		if(movie==null) {
			Movie newMovie = getMovieObject(movieRequest);
			Movie saved = movieRepo.save(newMovie);
			
			MovieResponse response = getMovieResponse(saved);
			
			ResponseStructure<MovieResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage(newMovie.getMovieName()+" Movie Data Created Successfully");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.CREATED);
		}
		else
			throw new RecordAlreadyExistException("This movie Already Exists");
	}

	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieById(int movieId) {
		Optional<Movie> movie=movieRepo.findById(movieId);
		if(movie.isPresent()) {
			Movie existing = movie.get();
			MovieResponse response = getMovieResponse(existing);
			response.setMovieRating(reviewRepo.avgRating(movieId));
			
			Map<String,String> map = new HashMap<>();
			map.put("reviews", "/"+existing.getMovieId()+"/reviews");
			response.setOptions(map);
			
			ResponseStructure<MovieResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Movie Data Found!");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.CREATED);
		}
		else
			throw new MovieNotFoundByIdException("No Movie Data with this ID");
	}

	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieByName(String movieName) {
		Movie movie = movieRepo.findMovieByMovieName(movieName);
		if(movie!=null) {
			MovieResponse response = getMovieResponse(movie);
			response.setMovieRating(reviewRepo.avgRating(movie.getMovieId()));
			
			Map<String, String> map = new HashMap<>();
			map.put("reviews", "/"+movie.getMovieId()+"/reviews");
			response.setOptions(map);
			
			ResponseStructure<MovieResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Movie Data Found!");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.FOUND);
		}
		else
			throw new MovieNotFoundByNameException("No Movie Found By this Name");
	}

	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> updateMovieById(MovieRequest request, int movieID) {
		Optional<Movie> optional = movieRepo.findById(movieID);
		if(optional.isPresent()) {
			Movie exists = optional.get();
			
			Movie update = getMovieObject(request);
			update.setMovieId(exists.getMovieId());
			movieRepo.save(update);
			
			MovieResponse response = getMovieResponse(update);
			
			ResponseStructure<MovieResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage(update.getMovieName()+" Movie Data Updated Successfully");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.OK);
			
		}
		else
			throw new MovieNotFoundByIdException("No Movie Data with this ID to UPDATE");
	}

	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> deleteMovieById(int movieId) {
		Optional<Movie> movie = movieRepo.findById(movieId);
		if(movie.isPresent()) {
			Movie exists = movie.get();
			movieRepo.delete(exists);
			
			MovieResponse response = getMovieResponse(exists);
			response.setMovieRating(reviewRepo.avgRating(movieId));
			
			ResponseStructure<MovieResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Movie Data Cleared Successfully");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.OK);
		}
		else
			throw new MovieNotFoundByIdException("No Such Movie present with this ID to Delete");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findAllMovies() {
		List<Movie> list = movieRepo.findAll();
		if(!list.isEmpty()) {
			List<MovieResponse> result = new ArrayList<>();
			
			for(Movie movie:list) {
				MovieResponse response = getMovieResponse(movie);
				response.setMovieRating(reviewRepo.avgRating(movie.getMovieId()));
				Map<String , String> map = new HashMap<>();
				map.put("reviews", "/"+movie.getMovieId()+"/reviews");
				response.setOptions(map);
				result.add(response);
			}
			
			ResponseStructure<List<MovieResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Movie Data Found!");
			structure.setData(result);
			
			return new ResponseEntity<ResponseStructure<List<MovieResponse>>>(structure,HttpStatus.FOUND);
			
		}
		else
			throw new MovieDataNotExistException("No Data Found");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findMoviesByGenres(String movieGenres) {
		
		Genres genre = Genres.valueOf(movieGenres); 
		
		List<Movie> movies = movieRepo.findMovieByMovieGenre(genre);
		if(!movies.isEmpty()) {
			List<MovieResponse> result = new ArrayList<>();
			for(Movie movie:movies) {
				MovieResponse response = getMovieResponse(movie);
				response.setMovieRating(reviewRepo.avgRating(movie.getMovieId()));
				
				Map<String , String> map = new HashMap<>();
				map.put("reviews", "/"+movie.getMovieId()+"/reviews");
				response.setOptions(map);
				
				result.add(response);
			}
			ResponseStructure<List<MovieResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Movie Data Found!! ");
			structure.setData(result);
			
			return new ResponseEntity<ResponseStructure<List<MovieResponse>>>(structure,HttpStatus.FOUND);
		}
		else
			throw new MovieDataNotExistException("No Movie Exists in "+movieGenres+" genre");
	}

}
