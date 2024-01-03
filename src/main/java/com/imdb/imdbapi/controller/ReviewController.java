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

import com.imdb.imdbapi.dto.ReviewRequest;
import com.imdb.imdbapi.dto.ReviewResponse;
import com.imdb.imdbapi.service.ReviewService;
import com.imdb.imdbapi.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class ReviewController {

	@Autowired
	private ReviewService service;
	
	@PostMapping("{userId}/{movieId}/reviews")
	public ResponseEntity<ResponseStructure<ReviewResponse>> addReview(@RequestBody @Valid ReviewRequest request,
																@PathVariable int userId,@PathVariable int movieId){
		return service.addReview(request, userId, movieId);
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<ResponseStructure<ReviewResponse>> findReviewById(@PathVariable int reviewId){
		return service.findReviewById(reviewId);
	}
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<ResponseStructure<ReviewResponse>> updateReviewById(@RequestBody @Valid ReviewRequest request,@PathVariable int reviewId){
		return service.updateReviewById(request, reviewId);
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<ResponseStructure<ReviewResponse>> deleteReviewById(@PathVariable int reviewId){
		return service.deleteReviewById(reviewId);
	}
	
	@GetMapping("/reviews")
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> allReview(){
		return service.allReview();
	}
	
	@GetMapping("/{movieId}/reviews")
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> findReviewFromMovieId(@PathVariable int movieId){
		return service.findReviewFromMovieId(movieId);
	}
	
}
