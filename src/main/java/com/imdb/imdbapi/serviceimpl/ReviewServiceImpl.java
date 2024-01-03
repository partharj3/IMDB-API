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

import com.imdb.imdbapi.dto.ReviewRequest;
import com.imdb.imdbapi.dto.ReviewResponse;
import com.imdb.imdbapi.entity.Movie;
import com.imdb.imdbapi.entity.Review;
import com.imdb.imdbapi.entity.User;
import com.imdb.imdbapi.exception.MovieNotFoundByIdException;
import com.imdb.imdbapi.exception.ReviewDataNotExistException;
import com.imdb.imdbapi.exception.ReviewNotFoundByIdException;
import com.imdb.imdbapi.exception.UserNotFoundByIdException;
import com.imdb.imdbapi.repository.MovieRepo;
import com.imdb.imdbapi.repository.ReviewRepo;
import com.imdb.imdbapi.repository.UserRepo;
import com.imdb.imdbapi.service.ReviewService;
import com.imdb.imdbapi.util.ResponseStructure;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	private Review getReview(ReviewRequest reviewReq) {
		Review review = new Review();
		review.setReviewMessage(reviewReq.getReviewMessage());
		review.setReviewRating(reviewReq.getReviewRating());
		return review;
	}
	
	private ReviewResponse getReviewResponse(Review review) {
		ReviewResponse response=new ReviewResponse();
		response.setReviewId(review.getReviewId());
		response.setReviewMessage(review.getReviewMessage());
		response.setReviewRating(review.getReviewRating());
		return response;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<ReviewResponse>> addReview(ReviewRequest request, int userId, int MovieId) {
		Optional<User> optUser = userRepo.findById(userId);
		Optional<Movie> optMovie = movieRepo.findById(MovieId);
		
		if(optUser.isPresent()) {
			if(optMovie.isPresent()) {
				Review review = getReview(request);
				review.setMovie(optMovie.get());
				review.setUser(optUser.get());
				
				Review saved = reviewRepo.save(review);
				
				ReviewResponse response = getReviewResponse(saved);
				Map<String, String> map = new HashMap<>();
				map.put("movies","/movies/"+review.getMovie().getMovieId());
				map.put("users","/users/"+review.getUser().getUserId());
				response.setOptions(map);
				
				ResponseStructure<ReviewResponse> structure = new ResponseStructure<>();
				structure.setStatusCode(HttpStatus.CREATED.value());
				structure.setMessage("Review Created Successfully!!");
				structure.setData(response);
				
				return new ResponseEntity<ResponseStructure<ReviewResponse>>(structure,HttpStatus.CREATED);
			}
			else
				throw new MovieNotFoundByIdException("No Movie Found by this ID");
		}
		else
			throw new UserNotFoundByIdException("No User Found by this ID");
		
	}

	@Override
	public ResponseEntity<ResponseStructure<ReviewResponse>> findReviewById(int reviewId) {
		Optional<Review> optional = reviewRepo.findById(reviewId);
		if(optional.isPresent()) {
			Review review = optional.get();
			
			ReviewResponse response = getReviewResponse(review);
			Map<String, String> map = new HashMap<>();
			map.put("movies","/movies/"+review.getMovie().getMovieId());
			map.put("users","/users/"+review.getUser().getUserId());
			response.setOptions(map);
			
			ResponseStructure<ReviewResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Data Found!!");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<ReviewResponse>>(structure,HttpStatus.FOUND);
		}
		else
			throw new ReviewNotFoundByIdException("No Review Record for this ID");
	}

	@Override
	public ResponseEntity<ResponseStructure<ReviewResponse>> updateReviewById(ReviewRequest request, int reviewId) {
		Optional<Review> optionalReview = reviewRepo.findById(reviewId);
		if(optionalReview.isPresent()) {
			Review existing = optionalReview.get();
			Review update = getReview(request);
			
			update.setReviewId(existing.getReviewId());
			Review saved = reviewRepo.save(update);
			
			ReviewResponse response = getReviewResponse(saved);
			
			ResponseStructure<ReviewResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("Review Record Updated Successsfully");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<ReviewResponse>>(structure,HttpStatus.ACCEPTED);
		}
		else
			throw new ReviewNotFoundByIdException("No Review Record for this ID to UPDATE");
	}

	@Override
	public ResponseEntity<ResponseStructure<ReviewResponse>> deleteReviewById(int reviewId) {
		Optional<Review> optionalReview = reviewRepo.findById(reviewId);
		if(optionalReview.isPresent()) {
			Review review = optionalReview.get();
			
			reviewRepo.delete(review);
			
			ReviewResponse response = getReviewResponse(review);
			
			ResponseStructure<ReviewResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Review Record Deleted Successsfully");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<ReviewResponse>>(structure,HttpStatus.OK);
		}
		else
			throw new ReviewNotFoundByIdException("No Review Record for this ID to DELETE");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> allReview() {
		List<Review> list = reviewRepo.findAll();
		if(!list.isEmpty()) {
			List<ReviewResponse> result = new ArrayList<>();
			
			for(Review review:list) {
				ReviewResponse response = getReviewResponse(review);
				Map<String, String> map = new HashMap<>();
				map.put("movies", "/movies/"+review.getMovie().getMovieId());
				map.put("users","/users/"+review.getUser().getUserId());
				response.setOptions(map);
				result.add(response);
			}
			
			ResponseStructure<List<ReviewResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Review Records Found");
			structure.setData(result);
			
			return new ResponseEntity<ResponseStructure<List<ReviewResponse>>>(structure,HttpStatus.FOUND);
		}
		else
			throw new ReviewDataNotExistException("No Review Records!!");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> findReviewFromMovieId(int movieId) {
		Optional<Movie> movie = movieRepo.findById(movieId);
		if(movie.isPresent()) {
			List<Review> reviewList = reviewRepo.findReviewByMovieId(movieId);
			if(!reviewList.isEmpty()){
				List<ReviewResponse> responseList = new ArrayList<>();
				for(Review review : reviewList) {
					
					ReviewResponse response = getReviewResponse(review);
					
					Map<String, String> map = new HashMap<>();
//					map.put("movies", "/movies/"+review.getMovie().getMovieId());
					map.put("users","/users/"+review.getUser().getUserId());
					response.setOptions(map);
					
					responseList.add(response);
				}
				
				ResponseStructure<List<ReviewResponse>> structure = new ResponseStructure<>();
				structure.setStatusCode(HttpStatus.FOUND.value());
				structure.setMessage("Review Found for the "+movie.get().getMovieName()+" Movie");
				structure.setData(responseList);

				return new ResponseEntity<ResponseStructure<List<ReviewResponse>>>(structure, HttpStatus.FOUND);
			} 	
			else	
				throw new ReviewDataNotExistException("No Reviews Found for "+movie.get().getMovieName()+" Movie");
		}
		else
			throw new MovieNotFoundByIdException("No Movie found by this ID");
	}
}
