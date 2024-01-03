package com.imdb.imdbapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imdb.imdbapi.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public User findUserByUserEmail(String email);
	
}
