package com.imdb.imdbapi.dto;

import java.time.LocalDate;

public class UserResponse {
	
	private int userId;
	private String userName;
	private String userEmail;
	private LocalDate userDOB;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String email) {
		this.userEmail = email;
	}
	public LocalDate getUserDOB() {
		return userDOB;
	}
	public void setUserDOB(LocalDate userDOB) {
		this.userDOB = userDOB;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
