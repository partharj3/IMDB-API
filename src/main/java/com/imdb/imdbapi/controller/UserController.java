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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.imdbapi.dto.UserRequest;
import com.imdb.imdbapi.dto.UserResponse;
import com.imdb.imdbapi.service.UserService;
import com.imdb.imdbapi.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody @Valid UserRequest userRequest) {
		return userService.addUser(userRequest);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable int userId){
		return userService.findUserById(userId);
	}
	
	@GetMapping("/users/mail")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserByEmail(@RequestParam String email){
		return userService.findUserByEmail(email);
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@RequestBody @Valid UserRequest request,@PathVariable int userId){
		return userService.updateUserById(request, userId);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(@PathVariable int userId){
		return userService.deleteUserById(userId);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers(){
		return userService.findAllUsers();
	}
	
}
