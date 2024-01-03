package com.imdb.imdbapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imdb.imdbapi.dto.UserRequest;
import com.imdb.imdbapi.dto.UserResponse;
import com.imdb.imdbapi.util.ResponseStructure;

@Service
public interface UserService {
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest);
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId);
	public ResponseEntity<ResponseStructure<UserResponse>> findUserByEmail(String email);
	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(UserRequest request,int userId);
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId);
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers();
	
}
