package com.imdb.imdbapi.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imdb.imdbapi.dto.UserRequest;
import com.imdb.imdbapi.dto.UserResponse;
import com.imdb.imdbapi.entity.User;
import com.imdb.imdbapi.exception.RecordAlreadyExistException;
import com.imdb.imdbapi.exception.UserDataNotExistException;
import com.imdb.imdbapi.exception.UserNotFoundByEmailException;
import com.imdb.imdbapi.exception.UserNotFoundByIdException;
import com.imdb.imdbapi.repository.UserRepo;
import com.imdb.imdbapi.service.UserService;
import com.imdb.imdbapi.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	private User getUser(UserRequest userReq) {
		User user = new User();	
		user.setUserName(userReq.getUserName());
		user.setUserEmail(userReq.getUserEmail());
		user.setUserPassword(userReq.getUserPassword());
		user.setUserDOB(userReq.getUserDOB());
		return user;
	}
	
	private UserResponse getUserResponse(User user) {
		UserResponse response=new UserResponse();
		response.setUserId(user.getUserId());
		response.setUserName(user.getUserName());
		response.setUserEmail(user.getUserEmail());
		response.setUserDOB(user.getUserDOB());
		return response;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest) {
		System.out.println(userRequest.getUserEmail());
		User exist = userRepo.findUserByUserEmail(userRequest.getUserEmail());
		
		if(exist==null) {
			User user = getUser(userRequest);
			User user2 = userRepo.save(user);
			UserResponse response = getUserResponse(user2);
			
			ResponseStructure<UserResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage("Data inserted Successfully!!");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.CREATED);
		}
		else
			throw new RecordAlreadyExistException("User Already Exist with this mail ID");
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId) {
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent()) {
			User existing = user.get();
			
			UserResponse response = getUserResponse(existing);
			
			ResponseStructure<UserResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("User Data Found");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.FOUND);
		}
		else
			throw new UserNotFoundByIdException("Can't Found user by this ID");
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserByEmail(String email) {
		User user = userRepo.findUserByUserEmail(email);
		if(user!=null) {
			UserResponse response = getUserResponse(user);
		
			ResponseStructure<UserResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("User Data Found");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFoundByEmailException("No Such User exist with this mail");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(UserRequest request, int userId) {
		Optional<User> user = userRepo.findById(userId); 
		if(user.isPresent()) {
			User existingUser = user.get();
			
			User updateUser =  getUser(request);
			updateUser.setUserId(existingUser.getUserId());
			
			User saved = userRepo.save(updateUser);
			
			UserResponse response = getUserResponse(saved);
			
			ResponseStructure<UserResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("User Data Updated Successfully!!");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.OK);
		}
		else
			throw new UserNotFoundByIdException("Can't Found user by this ID");
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId) {
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent()) {
			User user2 = user.get();
			userRepo.delete(user2);
			
			UserResponse response = getUserResponse(user2);
			
			ResponseStructure<UserResponse> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("User Data Deleted Successfully!! ");
			structure.setData(response);
			
			return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.OK);
		}
		else
			throw new UserNotFoundByIdException("Can't Found user by this ID");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers() {
		List<User> list = userRepo.findAll();
		if(!list.isEmpty()) {
			List<UserResponse> result = new ArrayList<>();
			
			for(User user:list) {
				UserResponse response = getUserResponse(user);
				result.add(response);
			}
			
			ResponseStructure<List<UserResponse>> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("User Records Found!!");
			structure.setData(result);
			
			return new ResponseEntity<ResponseStructure<List<UserResponse>>>(structure,HttpStatus.FOUND);
		}
		else
			throw new UserDataNotExistException("No User found !!");
	}

}
