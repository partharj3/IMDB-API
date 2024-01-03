package com.imdb.imdbapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.imdb.imdbapi.dto.ReviewRequest;
import com.imdb.imdbapi.dto.ReviewResponse;
import com.imdb.imdbapi.util.ResponseStructure;

public interface ReviewService {
	
	public ResponseEntity<ResponseStructure<ReviewResponse>> addReview(ReviewRequest request,int userId,int MovieId);
	public ResponseEntity<ResponseStructure<ReviewResponse>> findReviewById(int reviewId);
	public ResponseEntity<ResponseStructure<ReviewResponse>> updateReviewById(ReviewRequest request,int reviewId);
	public ResponseEntity<ResponseStructure<ReviewResponse>> deleteReviewById(int reviewId);
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> allReview();
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> findReviewFromMovieId(int movieId);
		
}