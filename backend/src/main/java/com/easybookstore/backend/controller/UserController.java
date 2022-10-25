package com.easybookstore.backend.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easybookstore.backend.model.Users;
import com.easybookstore.backend.request.UserRequest;
import com.easybookstore.backend.response.UserResponse;
import com.easybookstore.backend.service.UserService;

@RestController
@RequestMapping(value = "/easybook/v1/api")
public class UserController {
	@Autowired
	UserService service;
	
	@CrossOrigin(origins = "http://localhost:3000")//cors access for the frontend
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers(){
		List<Users> userList = new LinkedList<>();
		userList =  service.getUsers();
		if(userList.isEmpty()) {
			return new ResponseEntity<>(userList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:3000") //cors access for the frontend
	@PostMapping("/users")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
		UserResponse response = service.createUser(request);
		if(response.getId() == null) {
			return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
		
	}
	
}